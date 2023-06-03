package org.feup.Mutation_Testing_Backend_Final.Service.TestService;


import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Helper.Githelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.KadabraHelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.OperatorValidator;
import org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.XMLParser.TestCase;
import org.feup.Mutation_Testing_Backend_Final.Helper.XMLParser.TestSuite;
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import static org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper.extractTotalTimeGradle;
import static org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper.getGradleClassName;

@Service
public class GradleTestService {

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
    public GradleTestService(projectTestExecutionRepository projectTestExecutionRepository, testPackageRepository testPackageRepository, testClassRepository testClassRepository, testUnitRepository testUnitRepository, mutationOperatorRepository mutationOperatorRepository, mutationOperatorArgumentsRepository mutationOperatorArgumentsRepository) {
        this.projectTestExecutionRepository = projectTestExecutionRepository;
        this.testPackageRepository = testPackageRepository;
        this.testClassRepository = testClassRepository;
        this.testUnitRepository = testUnitRepository;
        this.mutationOperatorRepository = mutationOperatorRepository;
        this.mutationOperatorArgumentsRepository = mutationOperatorArgumentsRepository;
    }

    public SimpleResponse ExecuteAllTestsGradle(ProjectVersion projectVersion, ProjectTestExecution.TestExecutionType testExecutionTypeEnum, List<MutationOperator> operatorList) throws Exception {
        SimpleResponse sr = new SimpleResponse();

        // Changes the project version
        Githelper.updateCurrentVersion(projectsPath + projectVersion.getProject().getProjectPath(), projectVersion.getVersion());

        if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.NOMUTATION){
            Float totalElapsedTime = executeGradleTests(projectsPath + projectVersion.getProject().getProjectPath(), "");

            //Creates the Test Execution on the Database
            ProjectTestExecution projectTestExecution = new ProjectTestExecution(ProjectTestExecution.TestExecutionType.NOMUTATION, projectVersion);
            projectTestExecutionRepository.save(projectTestExecution);

            //Creates the tests in the database
            HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults;
            if(projectVersion.getProject().isAndroid()){
                testResults = getGradeTestResults(projectsPath + projectVersion.getProject().getProjectPath() +projectVersion.getProject().getAndroidBuildFolder() +File.separator +"build" + File.separator + "test-results"+ File.separator + "testDebugUnitTest");

                Float totalElapsedTimeAndroidTests = executeGradleTests(projectsPath + projectVersion.getProject().getProjectPath(), "connectedAndroidTest");
                totalElapsedTime += totalElapsedTimeAndroidTests;

                testResults.putAll(getGradeTestResults(projectsPath + projectVersion.getProject().getProjectPath() + projectVersion.getProject().getAndroidBuildFolder() +File.separator +"build" + File.separator + "outputs"+ File.separator + "androidTest-results" + File.separator + "connected"));

                Float testRunTime = getTestTime(testResults);
                boolean failedtest = saveTestResults(testResults, projectTestExecution);

                projectTestExecution.setFailedTests(failedtest);
                projectTestExecution.setTestRunTime(testRunTime);
                projectTestExecution.setCompilationTime(round(totalElapsedTime-testRunTime));
                projectTestExecution.setFailedCompilation(false);
                projectTestExecutionRepository.save(projectTestExecution);

                sr.setAsSuccess();
                sr.setData(projectTestExecution);
            }else{
                testResults = getGradeTestResults(projectsPath + projectVersion.getProject().getProjectPath() +projectVersion.getProject().getAndroidBuildFolder() + File.separator +"build" +File.separator +"test-results" + File.separator + "test");

                Float testRunTime = getTestTime(testResults);
                boolean failedtest = saveTestResults(testResults, projectTestExecution);

                projectTestExecution.setFailedTests(failedtest);
                projectTestExecution.setTestRunTime(testRunTime);
                projectTestExecution.setCompilationTime(round(totalElapsedTime-testRunTime));
                projectTestExecution.setFailedCompilation(false);
                projectTestExecutionRepository.save(projectTestExecution);

                sr.setAsSuccess();
                sr.setData(projectTestExecution);
            }
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

            System.out.println("Valid Operators: " + validOperators);
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

                    if(projectVersion.getProject().isAndroid()){
                        KadabraHelper.callKadabra(projectsPath + projectVersion.getProject().getProjectPath(), projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getTestFolder(), projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getAndroidTestFolder(), pathToKadabraIncludes, pathToKadabraEntryPoint, projectsPath, false, operatorNameList, operatorArgumentList, projectExecutionName, true);

                    }else{
                        KadabraHelper.callKadabra(projectsPath + projectVersion.getProject().getProjectPath(), projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getTestFolder(), pathToKadabraIncludes, pathToKadabraEntryPoint, projectsPath, false, operatorNameList, operatorArgumentList, projectExecutionName);

                        JSONParser parser = new JSONParser();
                        JSONArray array = (JSONArray) parser.parse(new FileReader(projectsPath + File.separator + projectExecutionName + File.separator + "MutationInfo.json"));
                        for (Object obj: array) {
                            JSONObject jsonObject = (JSONObject) obj;

                            //Executes the tests
                            Float totalElapsedTime = executeGradleTests(projectsPath + File.separator + projectExecutionName, " -DMUID=" + jsonObject.get("mutantId"));

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

                            HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults;
                            if(projectVersion.getProject().isAndroid()){
                                testResults = getGradeTestResults(projectsPath + File.separator + projectExecutionName + projectVersion.getProject().getAndroidBuildFolder() +File.separator +"build" + File.separator + "test-results"+ File.separator + "testDebugUnitTest");

                                Float totalElapsedTimeAndroidTests = executeGradleTests(projectsPath + File.separator + projectExecutionName, "connectedAndroidTest");
                                totalElapsedTime += totalElapsedTimeAndroidTests;

                                testResults.putAll(getGradeTestResults(projectsPath + File.separator + projectExecutionName + projectVersion.getProject().getAndroidBuildFolder() +File.separator +"build" + File.separator + "outputs"+ File.separator + "androidTest-results" + File.separator + "connected"));

                            }else{
                                testResults = getGradeTestResults(projectsPath + File.separator + projectExecutionName + File.separator +"build" +File.separator +"test-results" + File.separator + "test");
                            }

                            Float testRunTime = getTestTime(testResults);
                            boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);

                            projectTestExecutionChild.setFailedTests(failedtest);
                            projectTestExecutionChild.setFailedCompilation(false);
                            projectTestExecutionChild.setCompilationTime(totalElapsedTime-testRunTime);
                            projectTestExecutionChild.setTestRunTime(testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            projectTestExecutionTotalCompilationTime = OutputParsingHelper.round(projectTestExecutionTotalCompilationTime + OutputParsingHelper.round(totalElapsedTime-testRunTime));
                            projectTestExecutionTotalTestTime = OutputParsingHelper.round(projectTestExecutionTotalTestTime + testRunTime);

                            if (!projectTestExecution.isFailedTests() && failedtest){
                                projectTestExecution.setFailedTests(true);
                            }
                        }
                        projectTestExecution.setCompilationTime(projectTestExecutionTotalCompilationTime);
                        projectTestExecution.setTestRunTime(projectTestExecutionTotalTestTime);
                        projectTestExecution.setFailedCompilation(false);
                        projectTestExecutionRepository.save(projectTestExecution);

                        sr.setAsSuccess();
                        sr.setData(projectTestExecution);
                    }
                } else if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.TRADITIONALMUTATION) {
                    System.out.println("Traditional Mutation!!");
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

                    System.out.println("Kadabra Traditional Mutation!!");

                    if(projectVersion.getProject().isAndroid()){
                        KadabraHelper.callKadabra(projectsPath + projectVersion.getProject().getProjectPath(), projectsPath + projectVersion.getProject().getProjectPath()+ File.separator + projectVersion.getProject().getAndroidBuildFolder() + projectVersion.getProject().getTestFolder(), projectsPath + projectVersion.getProject().getProjectPath()+ File.separator + projectVersion.getProject().getAndroidBuildFolder()+ projectVersion.getProject().getAndroidTestFolder(), pathToKadabraIncludes, pathToKadabraEntryPoint, projectsPath, true, operatorNameList, operatorArgumentList, projectExecutionName, true);

                        JSONParser parser = new JSONParser();
                        JSONArray array = (JSONArray) parser.parse(new FileReader(projectsPath + File.separator + projectExecutionName + File.separator + "MutationInfo.json"));
                        for (Object obj: array) {
                            JSONObject jsonObject = (JSONObject) obj;

                            //Executes the tests
                            Float totalElapsedTime = executeGradleTests(projectsPath + File.separator + projectExecutionName + File.separator + jsonObject.get("mutantId"), "");

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


                            HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = getGradeTestResults(projectsPath + File.separator + projectExecutionName + jsonObject.get("mutantId")+ File.separator +"build" +File.separator +"test-results" + File.separator + "test");
                            Float testRunTime = getTestTime(testResults);
                            boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);

                            projectTestExecutionChild.setFailedTests(failedtest);
                            projectTestExecutionChild.setFailedCompilation(false);
                            projectTestExecutionChild.setCompilationTime(OutputParsingHelper.round(totalElapsedTime-testRunTime));
                            projectTestExecutionChild.setTestRunTime(testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            projectTestExecutionTotalCompilationTime = OutputParsingHelper.round(projectTestExecutionTotalCompilationTime + OutputParsingHelper.round(totalElapsedTime-testRunTime));
                            projectTestExecutionTotalTestTime = OutputParsingHelper.round(projectTestExecutionTotalTestTime + testRunTime);

                            if (!projectTestExecution.isFailedTests() && failedtest){
                                projectTestExecution.setFailedTests(true);
                            }
                        }
                        projectTestExecution.setCompilationTime(projectTestExecutionTotalCompilationTime);
                        projectTestExecution.setTestRunTime(projectTestExecutionTotalTestTime);
                        projectTestExecution.setFailedCompilation(false);
                        projectTestExecutionRepository.save(projectTestExecution);

                        sr.setAsSuccess();
                        sr.setData(projectTestExecution);
                    }else{
                        KadabraHelper.callKadabra(projectsPath + projectVersion.getProject().getProjectPath(), projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getTestFolder(), pathToKadabraIncludes, pathToKadabraEntryPoint, projectsPath, true, operatorNameList, operatorArgumentList, projectExecutionName);

                        JSONParser parser = new JSONParser();
                        JSONArray array = (JSONArray) parser.parse(new FileReader(projectsPath + File.separator + projectExecutionName + File.separator + "MutationInfo.json"));
                        for (Object obj: array) {
                            JSONObject jsonObject = (JSONObject) obj;

                            //Executes the tests
                            Float totalElapsedTime = executeGradleTests(projectsPath + File.separator + projectExecutionName + File.separator + jsonObject.get("mutantId"), "");

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


                            HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = getGradeTestResults(projectsPath + File.separator + projectExecutionName + jsonObject.get("mutantId")+ File.separator +"build" +File.separator +"test-results" + File.separator + "test");
                            Float testRunTime = getTestTime(testResults);
                            boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);

                            projectTestExecutionChild.setFailedTests(failedtest);
                            projectTestExecutionChild.setFailedCompilation(false);
                            projectTestExecutionChild.setCompilationTime(OutputParsingHelper.round(totalElapsedTime-testRunTime));
                            projectTestExecutionChild.setTestRunTime(testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            projectTestExecutionTotalCompilationTime = OutputParsingHelper.round(projectTestExecutionTotalCompilationTime + OutputParsingHelper.round(totalElapsedTime-testRunTime));
                            projectTestExecutionTotalTestTime = OutputParsingHelper.round(projectTestExecutionTotalTestTime + testRunTime);

                            if (!projectTestExecution.isFailedTests() && failedtest){
                                projectTestExecution.setFailedTests(true);
                            }
                        }
                        projectTestExecution.setCompilationTime(projectTestExecutionTotalCompilationTime);
                        projectTestExecution.setTestRunTime(projectTestExecutionTotalTestTime);
                        projectTestExecution.setFailedCompilation(false);
                        projectTestExecutionRepository.save(projectTestExecution);

                        sr.setAsSuccess();
                        sr.setData(projectTestExecution);

                    }
                }else{
                    sr.setAsError("Unknown project Execution");
                }
            }else{
                sr.setAsError("Invalid Operators");
            }
        }
        return sr;
    }

    public SimpleResponse ExecuteAllTestsGradleGit(ProjectVersion projectVersionFrom, ProjectVersion projectVersionTo, ProjectTestExecution.TestExecutionType testExecutionTypeEnum, List<MutationOperator> operatorList) throws Exception {
        SimpleResponse sr = new SimpleResponse();

        // Changes the project version
        Githelper.updateCurrentVersion(projectsPath + projectVersionTo.getProject().getProjectPath(), projectVersionFrom.getVersion());

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
            String projectExecutionName = projectVersionTo.getProject().getProjectName() + "_" + UUID.randomUUID();
            List<String> fileList = Githelper.getChangedFiles(projectVersionFrom.getVersion(), projectVersionTo.getVersion(), projectsPath + projectVersionTo.getProject().getProjectPath(), projectVersionFrom.getProject().getTestFolder());

            if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.GITIMMPROVEMENTMUTANTSCHEMATA){


            }else if (testExecutionTypeEnum == ProjectTestExecution.TestExecutionType.GITIMMPROVEMENTTRADITIONALMUTATION){

            }
            for (String i : fileList){
                System.out.println(i);
            }
        }

        return sr;
    }

    private Float executeGradleTests(String projectsPath, String aditionalCommand) throws IOException {
        String chmod = "chmod +x gradlew";
        Runtime.getRuntime().exec(chmod, null, new File(projectsPath));

        long start = System.currentTimeMillis();
        String command = "./gradlew ";
        if (aditionalCommand.contains("connectedAndroidTest")){
            command += aditionalCommand;
        }else{
            command += "test" + aditionalCommand;
        }

        System.out.println("Comando: " + command);

        Process process = Runtime.getRuntime().exec(command, null, new File(projectsPath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line, totalTimeLine = "";
        while ((line = reader.readLine()) != null) {
            if (line.contains("BUILD") && line.contains("in")) {
                totalTimeLine = line;
            }
            System.out.println(line);
        }

        long end = System.currentTimeMillis();

        Float totalCompilationTime;
        if (!totalTimeLine.equals("")){
            try {
                totalCompilationTime = extractTotalTimeGradle(totalTimeLine);
            }catch (Exception e){
                System.out.println(e);
                return (end - start) / 1000F;
            }
        }else{
            return (end - start) / 1000F;
        }

        return totalCompilationTime;
    }


    private static HashMap<String, HashMap<String, HashMap<String, TestUnit>>> getGradeTestResults(String path){
        System.out.println(path);
        HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = new HashMap<>();
        File reportFolder = new File(path);

        for (File reportFile: reportFolder.listFiles()){
            if (reportFile.isFile() && reportFile.getName().endsWith(".xml")){
                try {
                    JAXBContext jaxbContext = JAXBContext.newInstance(TestSuite.class);
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    TestSuite testSuite = (TestSuite) jaxbUnmarshaller.unmarshal(reportFile);

                    String className = getGradleClassName(testSuite.getName());
                    String packageName = testSuite.getName().replace("." + className, "");

                    HashMap<String, HashMap<String, TestUnit>> classList = testResults.computeIfAbsent(packageName, k -> new HashMap<>());
                    HashMap<String, TestUnit> testList = classList.computeIfAbsent(className, k -> new HashMap<>());

                    for (TestCase testCase : testSuite.getTestCases()) {
                        String testCaseName = testCase.getName().replace("(","").replace(")", "");
                        boolean failed = testCase.getFailureMessage() != null;
                        double time = testCase.getTime();
                        boolean skipped = false;

                        testList.put(testCaseName, new TestUnit(failed, (float) time, skipped, testCaseName));
                    }

                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        }
        return testResults;
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

    private float round(float number) {
        return BigDecimal.valueOf(number).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
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



}
