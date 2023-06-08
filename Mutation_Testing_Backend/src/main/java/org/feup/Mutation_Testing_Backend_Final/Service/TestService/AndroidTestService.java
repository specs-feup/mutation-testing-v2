package org.feup.Mutation_Testing_Backend_Final.Service.TestService;

import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Helper.XMLParser.TestCase;
import org.feup.Mutation_Testing_Backend_Final.Helper.XMLParser.TestSuite;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectMutantGeneration;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestClass;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestPackage;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestUnit;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectTestExecutionRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testClassRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testPackageRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Test.testUnitRepository;
import org.feup.Mutation_Testing_Backend_Final.Service.ProjectService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;

import static org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper.extractTotalTimeGradle;
import static org.feup.Mutation_Testing_Backend_Final.Helper.OutputParsingHelper.getGradleClassName;

@Service
public class AndroidTestService {
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

    private Logger logger;

    @Autowired
    public AndroidTestService(projectTestExecutionRepository projectTestExecutionRepository, testPackageRepository testPackageRepository, testClassRepository testClassRepository, testUnitRepository testUnitRepository) {
        this.projectTestExecutionRepository = projectTestExecutionRepository;
        this.testPackageRepository = testPackageRepository;
        this.testClassRepository = testClassRepository;
        this.testUnitRepository = testUnitRepository;
        logger = LoggerFactory.getLogger(ProjectService.class.getName());
    }

    public SimpleResponse ExecuteAllTests(ProjectTestExecution.TestExecutionType testExecutionType, ProjectMutantGeneration projectMutantGeneration) {
        SimpleResponse sr = new SimpleResponse();

        if (testExecutionType == ProjectTestExecution.TestExecutionType.NORMAL){
            logger.info("Normal Test Execution");
            if (projectMutantGeneration.getMutationGenerationType().equals(ProjectMutantGeneration.MutationGenerationType.MUTANTSCHEMATA) || projectMutantGeneration.getMutationGenerationType().equals(ProjectMutantGeneration.MutationGenerationType.TRADITIONALMUTATION)){
                ProjectTestExecution projectTestExecution = new ProjectTestExecution(testExecutionType, projectMutantGeneration);
                projectTestExecutionRepository.save(projectTestExecution);
                long startTotalElapsedTime = System.currentTimeMillis();
                float totalTestTime = 0;

                try {
                    String projectExecutionName = projectsPath + File.separator + projectMutantGeneration.getProjectExecutionName();
                    logger.debug("projectExecutionName: " + projectExecutionName);

                    JSONParser parser = new JSONParser();
                    JSONArray array = (JSONArray) parser.parse(new FileReader( projectExecutionName + File.separator + "MutationInfo.json"));

                    for (Object obj: array) {
                        JSONObject jsonObject = (JSONObject) obj;
                        JSONObject jsonObjectAux = (JSONObject) jsonObject.get("mutantion");

                        HashMap<String, HashMap<String, HashMap<String, TestUnit>>> testResults = null;

                        long startElapsedTime = System.currentTimeMillis();
                        long endElapsedTime = 0;

                        // Runs Java Specific Tests
                        if (!(boolean) jsonObjectAux.get("isAndroidSpecific")){
                            logger.debug("Running Java Specific");
                            if ((projectMutantGeneration.getMutationGenerationType().equals(ProjectMutantGeneration.MutationGenerationType.MUTANTSCHEMATA))){
                                executeJavaSpecificTests(projectExecutionName, " -DMUID=" + jsonObject.get("mutantId"));
                                endElapsedTime = System.currentTimeMillis();

                                testResults = getGradeTestResults(projectExecutionName + projectMutantGeneration.getProjectVersion().getProject().getBuildFolder() + File.separator  +"build" +File.separator +"test-results" + File.separator + "test");
                            }else{
                                executeJavaSpecificTests(projectExecutionName +  jsonObject.get("mutantId"), "");
                                endElapsedTime = System.currentTimeMillis();

                                testResults = getGradeTestResults(projectExecutionName + jsonObject.get("mutantId")+ projectMutantGeneration.getProjectVersion().getProject().getBuildFolder() + File.separator +"build" +File.separator +"test-results" + File.separator + "test");
                            }

                            Float testRunTime = getTestTime(testResults);
                            totalTestTime+=testRunTime;
                            ProjectTestExecution projectTestExecutionChild = new ProjectTestExecution(projectTestExecution, Integer.parseInt(String.valueOf(jsonObject.get("mutationLine"))), (String) jsonObject.get("filePath"), (String) jsonObject.get("mutantId"), testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);
                            projectTestExecutionChild.setFailedTests(failedtest);
                            projectTestExecutionChild.setFailedCompilation(false);

                            projectTestExecutionChild.setElaspedTime((endElapsedTime - startElapsedTime)/1000f);
                            projectTestExecutionChild.setTestRunTime(testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            if (!projectTestExecution.isFailedTests() && failedtest){
                                projectTestExecution.setFailedTests(true);
                            }

                        // Runs Android Specific Tests
                        }else{
                            logger.debug("Running Android Specific");
                            if ((projectMutantGeneration.getMutationGenerationType().equals(ProjectMutantGeneration.MutationGenerationType.MUTANTSCHEMATA))){
                                executeAndroidSpecificTests(projectExecutionName, (String) jsonObject.get("mutantId"));
                                endElapsedTime = System.currentTimeMillis();

                                testResults = getGradeTestResults(projectExecutionName + projectMutantGeneration.getProjectVersion().getProject().getBuildFolder() + File.separator  +"build" +File.separator +"test-results" + File.separator + "test");
                            }else{
                                executeAndroidSpecificTests(projectExecutionName +  jsonObject.get("mutantId"), "");
                                endElapsedTime = System.currentTimeMillis();

                                testResults = getGradeTestResults(projectExecutionName + jsonObject.get("mutantId")+ projectMutantGeneration.getProjectVersion().getProject().getBuildFolder() + File.separator +"build" +File.separator +"test-results" + File.separator + "test");
                            }

                            Float testRunTime = getTestTime(testResults);
                            totalTestTime+=testRunTime;
                            ProjectTestExecution projectTestExecutionChild = new ProjectTestExecution(projectTestExecution, Integer.parseInt(String.valueOf(jsonObject.get("mutationLine"))), (String) jsonObject.get("filePath"), (String) jsonObject.get("mutantId"), testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            boolean failedtest = saveTestResults(testResults, projectTestExecutionChild);
                            projectTestExecutionChild.setFailedTests(failedtest);
                            projectTestExecutionChild.setFailedCompilation(false);

                            projectTestExecutionChild.setElaspedTime((endElapsedTime - startElapsedTime)/1000f);
                            projectTestExecutionChild.setTestRunTime(testRunTime);
                            projectTestExecutionRepository.save(projectTestExecutionChild);

                            if (!projectTestExecution.isFailedTests() && failedtest){
                                projectTestExecution.setFailedTests(true);
                            }
                        }
                    }

                    long endTotalElapsedTime = System.currentTimeMillis();
                    projectTestExecution.setElaspedTime((endTotalElapsedTime - startTotalElapsedTime)/1000f);
                    projectTestExecution.setTestRunTime(totalTestTime);
                    projectTestExecution.setFailedCompilation(false);
                    projectTestExecutionRepository.save(projectTestExecution);

                    sr.setAsSuccess();
                    sr.setData(projectTestExecution);

                } catch (IOException e) {
                    logger.error(String.valueOf(e));

                    sr.setAsError("Error reading MutationInfo.json");
                } catch (ParseException e) {
                    logger.error(String.valueOf(e));

                    sr.setAsError("Error reading MutationInfo.json");
                }
            }else {
                sr.setAsError("Missing test execution implementation");
            }
        } else {
            sr.setAsError("Missing test execution implementation");
        }

        logger.info("Finished");
        return sr;
    }

    private Float executeJavaSpecificTests(String projectsPath, String aditionalCommand) throws IOException {
        logger.debug("Executing Tests for path: " + projectsPath);
        String chmod = "chmod +x gradlew";
        Runtime.getRuntime().exec(chmod, null, new File(projectsPath));

        long start = System.currentTimeMillis();
        String command = "./gradlew test";
        if (!aditionalCommand.isBlank()){
            command += aditionalCommand;
        }

        logger.debug("Comando: " + command + " in path: " + projectsPath);

        Process process = Runtime.getRuntime().exec(command, null, new File(projectsPath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line, totalTimeLine = "";
        while ((line = reader.readLine()) != null) {
            if (line.contains("BUILD") && line.contains("in")) {
                totalTimeLine = line;
            }
        }

        long end = System.currentTimeMillis();

        Float totalCompilationTime;
        if (!totalTimeLine.equals("")){
            try {
                totalCompilationTime = extractTotalTimeGradle(totalTimeLine);
            }catch (Exception e){
                return (end - start) / 1000F;
            }
        }else{
            return (end - start) / 1000F;
        }

        return totalCompilationTime;
    }

    private Float executeAndroidSpecificTests(String projectsPath, String aditionalCommand) throws IOException {
        logger.debug("Executing Android Tests for path: " + projectsPath);
        String chmod = "chmod +x gradlew";
        Runtime.getRuntime().exec(chmod, null, new File(projectsPath));

        long start = System.currentTimeMillis();
        String command = "./gradlew connectedAndroidTest";

        if (!aditionalCommand.isBlank()){
            logger.debug("Setting ADB MUID: " + aditionalCommand);
            String setProp = "adb shell setprop  MUID " + aditionalCommand;
            Runtime.getRuntime().exec(setProp, null, new File(projectsPath));
        }

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
                return (end - start) / 1000F;
            }
        }else{
            return (end - start) / 1000F;
        }

        return totalCompilationTime;
    }


    private HashMap<String, HashMap<String, HashMap<String, TestUnit>>> getGradeTestResults(String path){
        logger.info("Getting Test Results For Path: " + path);
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
        /*for (String packageKey: testResults.keySet()){
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
        }*/

        return failedtest;
    }

}
