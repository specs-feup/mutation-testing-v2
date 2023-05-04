package org.feup.Mutation_Testing_Backend_Final.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Model.MutationOperator.MutationOperator;
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

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("{projectVersionId}/executeAllTests")
    @Operation(summary = "Execute All tests")
    public ResponseEntity<SimpleResponse> executeAllTests(@PathVariable String projectVersionId, @RequestParam String testExecutionType, @RequestBody List<MutationOperator> operatorList) {
        SimpleResponse sr = new SimpleResponse();

        try{
            String result = testService.executeAllTests(projectVersionId, testExecutionType, operatorList);

            if (result.equals("")){
                return ResponseEntity.status(HttpStatus.OK).body(sr);
            }
        }catch (NumberFormatException e){
            sr.setAsError("Invalid Project Id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }catch (IllegalArgumentException e){
            sr.setAsError("Invalid Test Execution Type");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //Checks if the data is correct
        /*if (newProject.getId() != null && newProject.getCurrentProjectVersion() != null){
            sr.setAsError("Id must be null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }*/

        /*try {
            Project savedProject = projectService.addNewProject(newProject);

            sr.setData(savedProject);
            sr.setAsSuccess();
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        } catch (GitAPIException e) {
            sr.setAsError("Error cloning from git");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sr);
        } catch (IOException e) {
            sr.setAsError("Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sr);
        }*/

        return ResponseEntity.status(HttpStatus.OK).body(sr);
    }

    @PostMapping("test")
    public ResponseEntity<SimpleResponse> test() throws GitAPIException, IOException {
        SimpleResponse sr = new SimpleResponse();
        testService.getDiferences();
        return ResponseEntity.status(HttpStatus.OK).body(sr);
    }
}
