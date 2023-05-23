package org.feup.Mutation_Testing_Backend_Final.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.Project;
import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Service.ProjectTestExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/projectTestExecution")
public class ProjectTestExecutionController {
    private final ProjectTestExecutionService projectTestExecutionService;

    @Autowired
    public ProjectTestExecutionController(ProjectTestExecutionService projectTestExecutionService) {
        this.projectTestExecutionService = projectTestExecutionService;
    }

    @GetMapping("/getProjectTestExecution/{id}")
    @Operation(summary = "Get a Project Test Execution")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the Project Test Execution",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No Project Test Execution",
                    content = @Content)})
    public ResponseEntity<SimpleResponse> getExecution(@PathVariable String id){
        SimpleResponse sr = new SimpleResponse();
        sr.setAsSuccess();

        ProjectTestExecution projectTestExecution = projectTestExecutionService.getProjectTestExecution(Long.valueOf(id));
        if (projectTestExecution == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sr);
        }else{
            sr.setData(projectTestExecution);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }
    }
}
