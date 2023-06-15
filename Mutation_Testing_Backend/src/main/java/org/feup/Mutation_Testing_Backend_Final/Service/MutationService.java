package org.feup.Mutation_Testing_Backend_Final.Service;

import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.CreateMutantWrapper;
import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.MutationResultWrapper;
import org.feup.Mutation_Testing_Backend_Final.Helper.Githelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.KadabraHelper;
import org.feup.Mutation_Testing_Backend_Final.Helper.OperatorValidator;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperatorArguments;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectMutantGeneration;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectVersion;
import org.feup.Mutation_Testing_Backend_Final.Repository.MutationOperator.mutationOperatorArgumentsRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.MutationOperator.mutationOperatorRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectMutantGenerationRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectTestExecutionRepository;
import org.feup.Mutation_Testing_Backend_Final.Repository.Project.projectVersionRepository;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class MutationService {
    @Value("${pathToStoreProjects}")
    private String projectsPath;
    @Value("${pathToKadabraIncludes}")
    private String pathToKadabraIncludes;
    @Value("${pathToKadabraEntryPoint}")
    private String pathToKadabraEntryPoint;

    private final projectVersionRepository projectVersionRepository;
    private final mutationOperatorRepository mutationOperatorRepository;
    private final projectMutantGenerationRepository projectMutantGenerationRepository;
    private final mutationOperatorArgumentsRepository mutationOperatorArgumentsRepository;
    Logger logger;

    @Autowired
    public MutationService(projectVersionRepository projectVersionRepository, mutationOperatorRepository mutationOperatorRepository, projectMutantGenerationRepository projectMutantGenerationRepository, mutationOperatorArgumentsRepository mutationOperatorArgumentsRepository) {
        this.projectVersionRepository = projectVersionRepository;
        this.mutationOperatorRepository = mutationOperatorRepository;
        this.projectMutantGenerationRepository = projectMutantGenerationRepository;
        this.mutationOperatorArgumentsRepository = mutationOperatorArgumentsRepository;
        logger = LoggerFactory.getLogger(ProjectService.class.getName());
    }

    public SimpleResponse mutateProjectVersion(String projectVersionId, String testExecutionType, CreateMutantWrapper createMutantWrapper) {
        SimpleResponse sr = new SimpleResponse();

        Long i = Long.parseLong(projectVersionId);
        ProjectMutantGeneration.MutationGenerationType projectMutationGenerationType = ProjectMutantGeneration.MutationGenerationType.valueOf(testExecutionType.toUpperCase());

        Optional<ProjectVersion> projectVersionOptional = projectVersionRepository.findById(i);

        if (projectVersionOptional.isPresent()){
            ProjectVersion projectVersion = projectVersionOptional.get();
            List<MutationOperator> operatorList = createMutantWrapper.getOperatorList();

            logger.info("Setting project version");
            // Changes the project version
            if (Githelper.updateCurrentVersion(projectsPath + projectVersion.getProject().getProjectPath(), projectVersion.getVersion())){

                logger.info("Creating Argument List of Operators");
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
                    logger.info("Valid Operators");
                    String projectExecutionName = projectVersion.getProject().getProjectName() + "_" + UUID.randomUUID();

                    ProjectMutantGeneration projectMutantExecution = new ProjectMutantGeneration(projectMutationGenerationType, projectVersion, projectExecutionName);

                    boolean kadabraCallSucess;
                    logger.info("Calling Kadabra");
                    long start = System.currentTimeMillis();

                    if(projectVersion.getProject().isAndroid()){
                        kadabraCallSucess = KadabraHelper.callKadabra2(
                                projectsPath + projectVersion.getProject().getProjectPath(),
                                projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getTestFolder(),
                                projectsPath + projectVersion.getProject().getProjectPath()+ projectVersion.getProject().getAndroidTestFolder(),
                                pathToKadabraIncludes,
                                pathToKadabraEntryPoint,
                                projectsPath,
                                projectMutationGenerationType == ProjectMutantGeneration.MutationGenerationType.TRADITIONALMUTATION,
                                operatorNameList,
                                operatorArgumentList,
                                projectExecutionName,
                                true,
                                createMutantWrapper.getClassPath(),
                                createMutantWrapper.getUseIncompleteClassPath(),
                                projectMutationGenerationType
                        );

                    }else {
                        kadabraCallSucess = KadabraHelper.callKadabra2(
                                projectsPath + projectVersion.getProject().getProjectPath(),
                                projectsPath + projectVersion.getProject().getProjectPath() + projectVersion.getProject().getTestFolder(),
                                pathToKadabraIncludes,
                                pathToKadabraEntryPoint,
                                projectsPath,
                                projectMutationGenerationType == ProjectMutantGeneration.MutationGenerationType.TRADITIONALMUTATION,
                                operatorNameList,
                                operatorArgumentList,
                                projectExecutionName,
                                createMutantWrapper.getClassPath(),
                                createMutantWrapper.getUseIncompleteClassPath(),
                                projectMutationGenerationType
                        );
                    }

                    long finish = System.currentTimeMillis();
                    float timeElapsed = (finish - start)/1000f;
                    logger.info("End calling Kadabra - took " + timeElapsed + "s");
                    projectMutantExecution.setMutantGenerationTime(timeElapsed);
                    projectMutantExecution = projectMutantGenerationRepository.save(projectMutantExecution);

                    // Saves all of the operators in agregatted execution
                    for (MutationOperator mutationOperator: operatorList) {
                        MutationOperator mutationOperatorAux = new MutationOperator(mutationOperator.getOperador(), projectMutantExecution);
                        mutationOperatorRepository.save(mutationOperatorAux);

                        for(MutationOperatorArguments mutationOperatorArguments: mutationOperator.getMutationOperatorArgumentsList()){
                            MutationOperatorArguments mutationOperatorArgumentsAux = new MutationOperatorArguments(mutationOperatorArguments.getMutationOperatorArgument(), mutationOperatorAux);
                            mutationOperatorArgumentsRepository.save(mutationOperatorArgumentsAux);
                        }
                    }

                    if (kadabraCallSucess){
                        MutationResultWrapper mutationResultWrapper = getResults(projectExecutionName);
                        mutationResultWrapper.setTotalKadabraTime(timeElapsed);
                        mutationResultWrapper.setProjectMutantGeneration(projectMutantExecution);

                        sr.setAsSuccess();
                        sr.setData(mutationResultWrapper);
                    }else{
                        sr.setAsError("Error Calling Kadabra");
                    }

                }else{
                    sr.setAsError("Invalid Operators");
                }
            }else {
                sr.setAsError("Error Checking Git Version");
            }
        }else{
            sr.setAsError("Project Version Not Found");
        }

        return sr;
    }


    private MutationResultWrapper getResults(String projectExecutionName){
        MutationResultWrapper mutationResultWrapper = new MutationResultWrapper();
        HashMap<String, Integer> mutantGeneration = new HashMap<>();
        int numberOfMutations = 0;

        try {
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(new FileReader(projectsPath + File.separator + projectExecutionName + File.separator + "MutationInfo.json"));

            for (Object obj: array) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject jsonObjectAux = (JSONObject) jsonObject.get("mutantion");

                String operator = (String) jsonObjectAux.get("operator");
                if(mutantGeneration.containsKey(operator)){
                    mutantGeneration.replace(operator, mutantGeneration.get(operator) + 1);
                }else{
                    mutantGeneration.put(operator, 1);
                }

                numberOfMutations++;
            }

            mutationResultWrapper.setTotalQuantityOfMutants(numberOfMutations);
            mutationResultWrapper.setMutantGeneration(mutantGeneration);

        } catch (IOException | ParseException e) {
            logger.error(String.valueOf(e));
        }

        return mutationResultWrapper;
    }
}


