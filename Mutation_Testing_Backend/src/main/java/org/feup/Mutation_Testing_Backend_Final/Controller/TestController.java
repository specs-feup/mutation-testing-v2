package org.feup.Mutation_Testing_Backend_Final.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Service.ProjectTestExecutionService;
import org.feup.Mutation_Testing_Backend_Final.Service.TestService.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final ProjectTestExecutionService projectTestExecutionService;

    @Autowired
    public TestController(TestService testService, ProjectTestExecutionService projectTestExecutionService) {
        this.testService = testService;
        this.projectTestExecutionService = projectTestExecutionService;
    }

    @PostMapping("{projectVersionId}/executeAllTests")
    @Operation(summary = "Execute All tests")
    public ResponseEntity<SimpleResponse> executeAllTests(@PathVariable String projectVersionId, @RequestParam String testExecutionType, @RequestBody List<MutationOperator> operatorList) {
        SimpleResponse sr = new SimpleResponse();

        try{
            sr = testService.executeAllTests(projectVersionId, testExecutionType, operatorList);
            ProjectTestExecution aux = (ProjectTestExecution) sr.getData();

            //sr.setData(projectTestExecutionService.getProjectTestExecution(aux.getId()));

            return ResponseEntity.status(HttpStatus.OK).body(sr);

        }catch (NumberFormatException e){
            sr.setAsError("Invalid Project Id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }catch (IllegalArgumentException e){
            sr.setAsError("Invalid Test Execution Type");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/executeAllTestsGitImprovement")
    @Operation(summary = "Execute All tests")
    public ResponseEntity<SimpleResponse> executeAllTestsGitImprovement(@RequestParam String projectVersionIdFrom, @RequestParam String projectVersionIdTo, @RequestParam String testExecutionType, @RequestBody List<MutationOperator> operatorList) {
        SimpleResponse sr = new SimpleResponse();

        try{
            sr = testService.executeAllTestsGitImprovement(projectVersionIdFrom, projectVersionIdTo, testExecutionType, operatorList);

            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }catch (NumberFormatException e){
            sr.setAsError("Invalid Project Id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }catch (IllegalArgumentException e){
            sr.setAsError("Invalid Test Execution Type");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
