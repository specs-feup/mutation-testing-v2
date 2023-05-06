package org.feup.Mutation_Testing_Backend_Final.Service.TestService;

import org.apache.maven.plugin.surefire.log.api.NullConsoleLogger;
import org.apache.maven.plugins.surefire.report.ReportTestCase;
import org.apache.maven.plugins.surefire.report.ReportTestSuite;
import org.apache.maven.plugins.surefire.report.SurefireReportParser;
import org.apache.maven.reporting.MavenReportException;
import org.feup.Mutation_Testing_Backend_Final.Helper.Githelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.KadabraHelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.OperatorValidator;
import org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperatorArguments;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectVersion;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestClass;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestPackage;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestUnit;
import org.feup.Mutation_Testing_Backend_Final.Repository.MutationOperator.mutationOperatorArgumentsRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.MutationOperator.mutationOperatorRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectTestExecutionRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectVersionRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testClassRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testPackageRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testUnitRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class MavenTestService {
    @Value("${pathToStoreProjects}")
    private String projectsPath;
    @Value("${pathToKadabraIncludes}")
    private String pathToKadabraIncludes;
    @Value("${pathToKadabraEntryPoint}")
    private String pathToKadabraEntryPoint;

    private final projectTestExecutionRepository projectTestExecutionRepository;
    private final testPackageRepository testPackageRepository;
    private final testClassRepository testClassRepository;
    private final testUnitRepository testUnitRepository;
    private final mutationOperatorRepository mutationOperatorRepository;
    private final mutationOperatorArgumentsRepository mutationOperatorArgumentsRepository;


    @Autowired
    public MavenTestService(projectTestExecutionRepository projectTestExecutionRepository, testPackageRepository testPackageRepository, testClassRepository testClassRepository, testUnitRepository testUnitRepository, mutationOperatorRepository mutationOperatorRepository, mutationOperatorArgumentsRepository mutationOperatorArgumentsRepository) {
        this.projectTestExecutionRepository = projectTestExecutionRepository;
        this.testPackageRepository = testPackageRepository;
        this.testClassRepository = testClassRepository;
        this.testUnitRepository = testUnitRepository;
        this.mutationOperatorRepository = mutationOperatorRepository;
        this.mutationOperatorArgumentsRepository = mutationOperatorArgumentsRepository;
    }


    public void ExecuteAllTestsMaven(ProjectVersion projectVersion, ProjectTestExecution.TestExecutionType testExecutionTypeEnum, List<MutationOperator> operatorList) throws Exception {
        // Changes the project version
        Githelper.updateCurrentVersion(projectsPath + projectVersion.getProject().getProjectPath(), projectVersion.getVersion());

        if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.NOMUTATION){
            //Executes the tests
            Float totalElapsedTime = executeMavenTests(projectsPath + projectVersion.getProject().getProjectPath(), "");

            //Creates the Test Execution on the Database
            ProjectTestExecution projectTestExecution = new ProjectTestExecution(ProjectTestExecution.TestExecutionType.NOMUTATION, projectVersion);
            projectTestExecutionRepository.save(projectTestExecution);

            //Creates the tests in the database
            HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = getMavenTestResults(projectsPath + projectVersion.getProject().getProjectPath() + "/target/surefire-reports");
            Float testRunTime = getTestTime(testResults);
            boolean failedtest = saveTestResults(testResults, projectTestExecution);

            projectTestExecution.setFailedTests(failedtest);
            projectTestExecution.setTestRunTime(testRunTime);
            projectTestExecution.setCompilationTime(round(totalElapsedTime-testRunTime));
            projectTestExecution.setFailedCompilation(false);
            projectTestExecutionRepository.save(projectTestExecution);

        }else{
            // Cria a lista de argumentos e a lista com os nomes
            // operatorNameList = ["BinaryMutator", "BinaryMutator"]
            // operatorArgumentList = [["+", "-"],["+", "*"]]
            List<String> operatorNameList = new ArrayList<>();
            List<List<String>> operatorArgumentList = new ArrayList<>();
            for (MutationOperator mutationOperator: operatorList) {
                operatorNameList.add(mutationOperator.getOperador());
                List<String> argumentsAux = new ArrayList<>();
                if (mutationOperator.getMutationOperatorArgumentsList()!= null){
                    for (MutationOperatorArguments mutationOperatorArguments: mutationOperator.getMutationOperatorArgumentsList()){
                        argumentsAux.add(mutationOperatorArguments.getMutationOperatorArgument());
                    }
                }
                operatorArgumentList.add(argumentsAux);
            }

            OperatorValidator operatorValidator = new OperatorValidator();
            boolean validOperators = operatorValidator.validate(operatorNameList, operatorArgumentList);


            if (validOperators){
                String projectExecutionName = projectVersion.getProject().getProjectName() + "_" + UUID.randomUUID();

                if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.MUTANTSCHEMATA) {
                    // Creates the agregated execution
                    ProjectTestExecution projectTestExecution = new ProjectTestExecution(ProjectTestExecution.TestExecutionType.MUTANTSCHEMATA, projectVersion, projectExecutionName);
                    Float projectTestExecutionTotalCompilationTime = Float.parseFloat("0");
                    Float projectTestExecutionTotalTestTime = Float.parseFloat("0");
                    projectTestExecutionRepository.save(projectTestExecution);

                    // Saves all of the operators in agregatted execution
                    for (MutationOperator mutationOperator: operatorList) {
                        MutationOperator mutationOperatorAux = new MutationOperator(mutationOperator.getOperador(), projectTestExecution);
                        mutationOperatorRepository.save(mutationOperatorAux);

                        for(MutationOperatorArguments mutationOperatorArguments: mutationOperator.getMutationOperatorArgumentsList()){
                            MutationOperatorArguments mutationOperatorArgumentsAux = new MutationOperatorArguments(mutationOperatorArguments.getMutationOperatorArgument(), mutationOperatorAux);
                            mutationOperatorArgumentsRepository.save(mutationOperatorArgumentsAux);
                        }
                    }

                    KadabraHelper.callKadabra(projectsPath + projectVersion.getProject().getProjectPath(), projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getTestFolder(), pathToKadabraIncludes, pathToKadabraEntryPoint, projectsPath, false, operatorNameList, operatorArgumentList, projectExecutionName);

                    JSONParser parser = new JSONParser();
                    JSONArray array = (JSONArray) parser.parse(new FileReader(projectsPath + File.separator + projectExecutionName + File.separator + "MutationInfo.json"));
                    for (Object obj: array) {
                        JSONObject jsonObject = (JSONObject) obj;

                        //Executes the tests
                        Float totalElapsedTime = executeMavenTests(projectsPath + File.separator + projectExecutionName, " -DMUID=" + jsonObject.get("mutantId"));

                        ProjectTestExecution projectTestExecutionChild = new ProjectTestExecution(ProjectTestExecution.TestExecutionType.MUTANTSCHEMATA, projectVersion, projectTestExecution, Integer.parseInt(String.valueOf(jsonObject.get("mutationLine"))), (String) jsonObject.get("filePath"), (String) jsonObject.get("mutantId"));
                        projectTestExecutionRepository.save(projectTestExecutionChild);


                        JSONObject jsonObjectAux = (JSONObject) jsonObject.get("mutantion");
                        MutationOperator mutationOperatorAux = new MutationOperator((String) jsonObjectAux.get("operator"), projectTestExecutionChild);
                        mutationOperatorRepository.save(mutationOperatorAux);

                        JSONArray jsonArray = (JSONArray) jsonObjectAux.get("mutationOperatorArgumentsList");
                        for(Object mutationOperatorArguments: jsonArray){
                            MutationOperatorArguments mutationOperatorArgumentsAux = new MutationOperatorArguments((String) mutationOperatorArguments, mutationOperatorAux);
                            mutationOperatorArgumentsRepository.save(mutationOperatorArgumentsAux);
                        }

                        HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = getMavenTestResults(projectsPath + File.separator + projectExecutionName + File.separator +"target" +File.separator +"surefire-reports");
                        Float testRunTime = getTestTime(testResults);
                        boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);

                        projectTestExecutionChild.setFailedTests(failedtest);
                        projectTestExecutionChild.setFailedCompilation(false);
                        projectTestExecutionChild.setCompilationTime(totalElapsedTime-testRunTime);
                        projectTestExecutionChild.setTestRunTime(testRunTime);
                        projectTestExecutionRepository.save(projectTestExecutionChild);

                        projectTestExecutionTotalCompilationTime = round(projectTestExecutionTotalCompilationTime + round(totalElapsedTime-testRunTime));
                        projectTestExecutionTotalTestTime = round(projectTestExecutionTotalTestTime + testRunTime);

                        if (!projectTestExecution.isFailedTests() && failedtest){
                            projectTestExecution.setFailedTests(true);
                        }
                    }
                    projectTestExecution.setCompilationTime(projectTestExecutionTotalCompilationTime);
                    projectTestExecution.setTestRunTime(projectTestExecutionTotalTestTime);
                    projectTestExecution.setFailedCompilation(false);
                    projectTestExecutionRepository.save(projectTestExecution);


                } else if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.TRADITIONALMUTATION) {
                    ProjectTestExecution projectTestExecution = new ProjectTestExecution(ProjectTestExecution.TestExecutionType.TRADITIONALMUTATION, projectVersion, projectExecutionName);
                    Float projectTestExecutionTotalCompilationTime = Float.parseFloat("0");
                    Float projectTestExecutionTotalTestTime = Float.parseFloat("0");
                    projectTestExecutionRepository.save(projectTestExecution);

                    for (MutationOperator mutationOperator: operatorList) {
                        MutationOperator mutationOperatorAux = new MutationOperator(mutationOperator.getOperador(), projectTestExecution);
                        mutationOperatorRepository.save(mutationOperatorAux);

                        for(MutationOperatorArguments mutationOperatorArguments: mutationOperator.getMutationOperatorArgumentsList()){
                            MutationOperatorArguments mutationOperatorArgumentsAux = new MutationOperatorArguments(mutationOperatorArguments.getMutationOperatorArgument(), mutationOperatorAux);
                            mutationOperatorArgumentsRepository.save(mutationOperatorArgumentsAux);
                        }
                    }

                    KadabraHelper.callKadabra(projectsPath + projectVersion.getProject().getProjectPath(), projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getTestFolder(), pathToKadabraIncludes, pathToKadabraEntryPoint, projectsPath, true, operatorNameList, operatorArgumentList, projectExecutionName);

                    JSONParser parser = new JSONParser();
                    JSONArray array = (JSONArray) parser.parse(new FileReader(projectsPath + File.separator + projectExecutionName + File.separator + "MutationInfo.json"));
                    for (Object obj: array) {
                        JSONObject jsonObject = (JSONObject) obj;

                        //Executes the tests
                        Float totalElapsedTime = executeMavenTests(projectsPath + File.separator + projectExecutionName + File.separator + jsonObject.get("mutantId"), "");

                        ProjectTestExecution projectTestExecutionChild = new ProjectTestExecution(ProjectTestExecution.TestExecutionType.TRADITIONALMUTATION, projectVersion, projectTestExecution, Integer.parseInt(String.valueOf(jsonObject.get("mutationLine"))), (String) jsonObject.get("filePath"), (String) jsonObject.get("mutantId"));
                        projectTestExecutionRepository.save(projectTestExecutionChild);

                        JSONObject jsonObjectAux = (JSONObject) jsonObject.get("mutantion");
                        MutationOperator mutationOperatorAux = new MutationOperator((String) jsonObjectAux.get("operator"), projectTestExecutionChild);
                        mutationOperatorRepository.save(mutationOperatorAux);

                        JSONArray jsonArray = (JSONArray) jsonObjectAux.get("mutationOperatorArgumentsList");
                        for(Object mutationOperatorArguments: jsonArray){
                            MutationOperatorArguments mutationOperatorArgumentsAux = new MutationOperatorArguments((String) mutationOperatorArguments, mutationOperatorAux);
                            mutationOperatorArgumentsRepository.save(mutationOperatorArgumentsAux);
                        }


                        HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = getMavenTestResults(projectsPath + File.separator + projectExecutionName + jsonObject.get("mutantId")+ File.separator +"target" +File.separator +"surefire-reports");
                        Float testRunTime = getTestTime(testResults);
                        boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);

                        projectTestExecutionChild.setFailedTests(failedtest);
                        projectTestExecutionChild.setFailedCompilation(false);
                        projectTestExecutionChild.setCompilationTime(round(totalElapsedTime-testRunTime));
                        projectTestExecutionChild.setTestRunTime(testRunTime);
                        projectTestExecutionRepository.save(projectTestExecutionChild);

                        projectTestExecutionTotalCompilationTime = round(projectTestExecutionTotalCompilationTime + round(totalElapsedTime-testRunTime));
                        projectTestExecutionTotalTestTime = round(projectTestExecutionTotalTestTime + testRunTime);

                        if (!projectTestExecution.isFailedTests() && failedtest){
                            projectTestExecution.setFailedTests(true);
                        }
                    }
                    projectTestExecution.setCompilationTime(projectTestExecutionTotalCompilationTime);
                    projectTestExecution.setTestRunTime(projectTestExecutionTotalTestTime);
                    projectTestExecution.setFailedCompilation(false);
                    projectTestExecutionRepository.save(projectTestExecution);
                }
            }else{
                System.out.println("Invalid Operators");
            }
        }
    }

    private Float getTestTime(HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults) {
        Float testTime = Float.parseFloat("0");
        for(String packageName: testResults.keySet()){
            for (String className: testResults.get(packageName).keySet()){
                for(String testname: testResults.get(packageName).get(className).keySet()){
                    testTime = round(testTime + testResults.get(packageName).get(className).get(testname).getTestRunTime());
                }
            }
        }
        return testTime;
    }

    private Float executeMavenTests(String projectsPath, String aditionalCommand) throws IOException {
        String command = "mvn surefire-report:report" + aditionalCommand;
        Process process = Runtime.getRuntime().exec(command, null, new File(projectsPath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line, totalTimeLine = "";
        while ((line = reader.readLine()) != null) {
            if (line.contains("Total time:")) {
                totalTimeLine = line;
            }
        }
        reader.close();

        Float totalCompilationTime = Float.parseFloat("9999.9");
        if (!totalTimeLine.equals("")){
            try {
                totalCompilationTime = round(OutputParsingHelper.extractTotalTime(totalTimeLine));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        return totalCompilationTime;
    }

    private boolean saveTestResults(HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults, ProjectTestExecution projectTestExecution) {
        boolean failedtest = false;
        for (String packageKey: testResults.keySet()){
            TestPackage testPackage = new TestPackage(packageKey, projectTestExecution);
            testPackageRepository.save(testPackage);

            for (String classKey: testResults.get(packageKey).keySet()){
                TestClass testClass = new TestClass(classKey, testPackage);
                testClassRepository.save(testClass);

                for (String testKey: testResults.get(packageKey).get(classKey).keySet()){
                    TestUnit testUnitAux = testResults.get(packageKey).get(classKey).get(testKey);
                    testUnitAux.setTestClass(testClass);
                    testUnitRepository.save(testUnitAux);

                    if (testUnitAux.isFailed()){
                        failedtest = true;
                    }
                }
            }
        }

        return failedtest;
    }


    private HashMap<String, HashMap<String, HashMap<String, TestUnit>>> getMavenTestResults(String path) throws MavenReportException {
        File reports = new File(path);
        List<File> aux = new ArrayList<>();
        aux.add(reports);
        SurefireReportParser parser = new SurefireReportParser(aux, Locale.ENGLISH, new NullConsoleLogger());

        List<ReportTestSuite> suites = parser.parseXMLReportFiles();
        HashMap<String, HashMap<String, TestUnit>> testes = new HashMap<>();
        for (ReportTestSuite suite : suites) {
            String className = suite.getFullClassName().replace(suite.getPackageName()+".", "");

            for(ReportTestCase testCase :suite.getTestCases()){
                String testCaseName = testCase.getName();

                if (testes.containsKey(className)){
                    HashMap<String, TestUnit> hashMapTestCase = testes.get(className);

                    if (hashMapTestCase.containsKey(testCaseName)){
                        hashMapTestCase.replace(testCaseName, new TestUnit(
                                !testCase.isSuccessful() && hashMapTestCase.get(testCaseName).isFailed(),
                                round(hashMapTestCase.get(testCaseName).getTestRunTime()+testCase.getTime()),
                                testCase.hasSkipped() && hashMapTestCase.get(testCaseName).isSkiped(),
                                testCaseName
                        ));
                        testes.replace(className, hashMapTestCase);
                    }else{
                        hashMapTestCase.put(testCaseName, new TestUnit(!testCase.isSuccessful(), round(testCase.getTime()), testCase.hasSkipped(), testCaseName));
                        testes.replace(className, hashMapTestCase);
                    }
                }else{
                    HashMap<String, TestUnit> hashMapTestCase = new HashMap<>();
                    hashMapTestCase.put(testCaseName, new TestUnit(!testCase.isSuccessful(),round(testCase.getTime()), testCase.hasSkipped(), testCaseName));
                    testes.put(className, hashMapTestCase);
                }
            }
        }

        HashMap<String, HashMap<String, HashMap<String, TestUnit>>> finalResult = new HashMap<>();
        for (ReportTestSuite suite : suites) {
            String packageName = suite.getPackageName();
            String className = suite.getFullClassName().replace(suite.getPackageName()+".", "");

            if (finalResult.containsKey(packageName)){
                finalResult.get(packageName).put(className, testes.get(className));
            }else{
                HashMap<String, HashMap<String, TestUnit>> auxHashMap = new HashMap<>();
                auxHashMap.put(className, testes.get(className));
                finalResult.put(packageName, auxHashMap);
            }

        }

        return finalResult;
    }

    private float round(float number) {
        return BigDecimal.valueOf(number).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}
