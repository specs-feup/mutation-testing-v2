package org.feup.Mutation_Testing_Backend_Final.Service;

import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MavenTestsService {
    public Map<String, List<String>> getAllTests(ProjectPathWrapper projectPathWrapper) throws IOException {
        Map<String, List<String>> testClassesAndCases = new HashMap<>();
        try {
            Files.walk(Paths.get(projectPathWrapper.getProjectPath())).forEach(filePath -> {
                if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".java")) {
                    System.out.println(filePath);

                    String fileContent = null;
                    try {
                        fileContent = Files.readString(filePath, StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }

                    String testClassName = extractTestsClassName(fileContent);
                    // If the file does not have a class
                    if (testClassName != null){
                        List<String> testCaseNames = extractTestsCaseNames(fileContent);

                        // If there are tests in the class
                        if (!testCaseNames.isEmpty()){
                            testClassesAndCases.put(testClassName, testCaseNames);
                        }
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        System.out.println(testClassesAndCases);
        return testClassesAndCases;
    }

    private String extractTestsClassName(String fileContent) {
        String testClassName = null;
        Pattern pattern = Pattern.compile("public class (\\w+)");
        Matcher matcher = pattern.matcher(fileContent);
        if (matcher.find()) {
            testClassName = matcher.group(1);
        }
        return testClassName;
    }

    private List<String> extractTestsCaseNames(String fileContent) {
        List<String> testCaseNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("@Test\\s+public void (\\w+)\\(");
        Matcher matcher = pattern.matcher(fileContent);
        while (matcher.find()) {
            testCaseNames.add(matcher.group(1));
        }
        return testCaseNames;
    }

    public String executeAllTests(ProjectPathWrapper projectPathWrapper) throws IOException, InterruptedException {
        return runTests(projectPathWrapper.getProjectPath(), "mvn test");
    }

    public String executeTestClass(ProjectClassWrapper projectClassWrapper)throws IOException, InterruptedException {
        return runTests(projectClassWrapper.getProjectPath(),"mvn test -Dtest=\"" + projectClassWrapper.getTestClass() + "\"");
    }

    public String executeUnitTest(ProjectUnitTestWrapper projectUnitTestWrapper)throws IOException, InterruptedException {
        return runTests(projectUnitTestWrapper.getProjectPath(),"mvn test -Dtest=\"" + projectUnitTestWrapper.getTestClass() + "#"+ projectUnitTestWrapper.getUnitTest() + "\"");
    }

    public HashMap<String, String>  executeAllTestsMutantSchemata(ProjectPathWrapper projectPathWrapper) throws IOException, InterruptedException, ParseException {
        Object o = new JSONParser().parse(new FileReader(projectPathWrapper.getProjectPath() + "/MutationInfo.json"));
        JSONArray jarray = (JSONArray) o;

        HashMap<String, String> result = new HashMap<>();

        for (int i=0; i < jarray.size(); i++) {
            String mutantId = (String) ((JSONObject)jarray.get(i)).get("mutantId");
            String testResult = runTests(projectPathWrapper.getProjectPath()+"/MetaMutante", "mvn test -DMUID=\"" + mutantId + "\"");
            System.out.println(testResult);
            result.put(mutantId,testResult);
        }

        return result;
    }

    public String executeTestClassMutantSchemata(ProjectClassMutantSchemataWrapper projectClassMutantSchemataWrapper)throws IOException, InterruptedException {
        return runTests(projectClassMutantSchemataWrapper.getProjectPath(),"mvn test -Dtest=\"" + projectClassMutantSchemataWrapper.getTestClass() + "\" -DMUID=\"" + projectClassMutantSchemataWrapper.getMutantSchemata() + "\"");
    }

    public String executeUnitTestMutantSchemata(ProjectUnitTestMutantSchemataWrapper projectUnitTestMutantSchemataWrapper)throws IOException, InterruptedException {
        return runTests(projectUnitTestMutantSchemataWrapper.getProjectPath(),"mvn test -Dtest=\"" + projectUnitTestMutantSchemataWrapper.getTestClass() + "#"+ projectUnitTestMutantSchemataWrapper.getUnitTest() + "\" -DMUID=\"" + projectUnitTestMutantSchemataWrapper.getMutantSchemata() + "\"");
    }

    private String runTests(String projectPath, String executeComand) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \""+ projectPath + "\" && "+ executeComand);
        builder.redirectErrorStream(true);

        Process process = builder.start();
        int exitCode = process.waitFor();

        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String output = "", line;

        Pattern pattern;
        Matcher matcher;

        if (exitCode == 0){
            pattern = Pattern.compile("^\\[INFO\\] Tests run: (\\d+), Failures: (\\d+), Errors: (\\d+), Skipped: (\\d+), Time elapsed: (\\d+(?:\\.\\d+)?)\\s*(\\w+) - in (\\w+)$");
        }else{
            pattern = Pattern.compile("^\\[ERROR\\] Tests run: (\\d+), Failures: (\\d+), Errors: (\\d+), Skipped: (\\d+)$");
        }

        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }

            matcher = pattern.matcher(line);
            if (matcher.find()) {
                output += line;
            }
        }

        return output;
    }
}
