package org.feup.Mutation_Testing_Backend_Final.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Model.Project;
import org.feup.Mutation_Testing_Backend_Final.Model.ProjectVersion;
import org.feup.Mutation_Testing_Backend_Final.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getAllProjects")
    @Operation(summary = "Get all projetcs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all projects",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No projects",
                    content = @Content)})
    public ResponseEntity<SimpleResponse> getAll(){
        SimpleResponse sr = new SimpleResponse();
        sr.setAsSuccess();

        List<Project> savedProject = projectService.getAllProjects();
        if (savedProject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sr);
        }else{
            sr.setData(savedProject);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }
    }

    @PostMapping("/addNewProject")
    @Operation(summary = "Add a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tests in the project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error in the data recieved",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error cloning from git",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> addNewProject(@RequestBody Project newProject) {
        SimpleResponse sr = new SimpleResponse();

        //Checks if the data is correct
        if (newProject.getId() != null){
            sr.setAsError("Id must be null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }

        try {
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
        }
    }


    @GetMapping("/getProjectVersions/{projectId}")
    @Operation(summary = "Get Current Project Versions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found versions of the project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No project Versions",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the data recieved",
                    content = @Content)})
    public ResponseEntity<SimpleResponse> getProjectVersions(@RequestParam Long projectId) {
        SimpleResponse sr = new SimpleResponse();

        //Checks if the data is correct
        if (projectId == null || projectId < 1){
            sr.setAsError("You must especify a valid project ID");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }

        List<ProjectVersion> projectVersionList = projectService.getProjectVersions(projectId);
        sr.setAsSuccess();

        if (projectVersionList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sr);
        }else{
            sr.setData(projectVersionList);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }
    }

    @PostMapping("/setProjectVersions")
    @Operation(summary = "Get Current Project Versions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found versions of the project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No project Versions",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the data recieved",
                    content = @Content)})
    public ResponseEntity<SimpleResponse> getProjectVersions(@RequestBody Project project) {
        SimpleResponse sr = new SimpleResponse();

        //Checks if the data is correct
        if (project.getId() == null || project.getId() < 1 || project.getCurrentProjectVersion() > 0){
            sr.setAsError("You must especify a valid project ID and project version");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }

        Project projectNew = projectService.setProjectVersion(project);
        sr.setAsSuccess();

        if (projectVersionList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sr);
        }else{
            sr.setData(projectVersionList);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }
    }

}