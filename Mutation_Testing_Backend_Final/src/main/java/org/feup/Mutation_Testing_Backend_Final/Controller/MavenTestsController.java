package org.feup.Mutation_Testing_Backend_Final.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.*;
import org.feup.Mutation_Testing_Backend_Final.Service.MavenTestsService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/mavenTests")
public class MavenTestsController {
    private final MavenTestsService mavenTestsService;
    @Autowired
    public MavenTestsController(MavenTestsService mavenTestsService) {
        this.mavenTestsService = mavenTestsService;
    }

    @PostMapping("/getAll")
    @Operation(summary = "Get all tests grouped by class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found tests in the project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "Did not find any tests in the project",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> getAll(@RequestBody ProjectPathWrapper projectPathWrapper){
        System.out.println("Entrou");

        SimpleResponse mr = new SimpleResponse();
        try {
            Map<String, List<String>> result = mavenTestsService.getAllTests(projectPathWrapper);

            mr.setData(result);
            mr.setAsSuccess();

            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mr);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(mr);
            }

        } catch (IOException e) {
            mr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mr);
        }
    }

    @PostMapping("/traditionalMutation/executeIndividualClassTests")
    @Operation(summary = "Execute all the tests from a class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finished running the tests",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error running the tests",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> executeIndividualClassTests(@RequestBody ProjectClassWrapper projectClassWrapper){
        SimpleResponse sr = new SimpleResponse();
        try {
            String testsOutput = mavenTestsService.executeTestClass(projectClassWrapper);

            sr.setAsSuccess(testsOutput);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        } catch (IOException e) {
            sr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
        } catch (InterruptedException e) {
            sr.setAsError("Error running tests");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }
    }

    @PostMapping("/traditionalMutation/executeIndividualTest")
    @Operation(summary = "Execute a unit test from a class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finished running the tests",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error running the tests",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> executeIndividualTest(@RequestBody ProjectUnitTestWrapper projectUnitTestWrapper){
        SimpleResponse sr = new SimpleResponse();
        try {
            String testsOutput = mavenTestsService.executeUnitTest(projectUnitTestWrapper);

            sr.setAsSuccess(testsOutput);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        } catch (IOException e) {
            sr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
        } catch (InterruptedException e) {
            sr.setAsError("Error running tests");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }
    }

    @PostMapping("/traditionalMutation/executeAll")
    @Operation(summary = "Execute All tests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finished running the tests",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error running the tests",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> executeAll(@RequestBody ProjectPathWrapper projectPathWrapper){
        SimpleResponse sr = new SimpleResponse();
        try {
            String testsOutput = mavenTestsService.executeAllTests(projectPathWrapper);

            sr.setAsSuccess(testsOutput);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        } catch (IOException e) {
            sr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
        } catch (InterruptedException e) {
            sr.setAsError("Error running tests");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }
    }

    @PostMapping("/mutationSchemata/executeIndividualClassTests")
    @Operation(summary = "Execute all the tests from a class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finished running the tests",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error running the tests",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> executeIndividualClassTestsMutationSchemata(@RequestBody ProjectClassMutantSchemataWrapper projectClassWrapper){
        SimpleResponse sr = new SimpleResponse();
        try {
            String testsOutput = mavenTestsService.executeTestClassMutantSchemata(projectClassWrapper);

            sr.setAsSuccess(testsOutput);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        } catch (IOException e) {
            sr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
        } catch (InterruptedException e) {
            sr.setAsError("Error running tests");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }
    }

    @PostMapping("/mutationSchemata/executeIndividualTest")
    @Operation(summary = "Execute a unit test from a class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finished running the tests",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error running the tests",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> executeIndividualTestMutationSchemata(@RequestBody ProjectUnitTestMutantSchemataWrapper projectUnitTestWrapper){
        SimpleResponse sr = new SimpleResponse();
        try {
            String testsOutput = mavenTestsService.executeUnitTestMutantSchemata(projectUnitTestWrapper);

            sr.setAsSuccess(testsOutput);
            return ResponseEntity.status(HttpStatus.OK).body(sr);
        } catch (IOException e) {
            sr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sr);
        } catch (InterruptedException e) {
            sr.setAsError("Error running tests");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }
    }

    @PostMapping("/mutationSchemata/executeAll")
    @Operation(summary = "Execute All tests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finished running the tests",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Error running the tests",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Did not find the project",
                    content = @Content) })
    public ResponseEntity<SimpleResponse> executeAllMutationSchemata(@RequestBody ProjectPathWrapper projectPathWrapper){
        SimpleResponse mr = new SimpleResponse();
        try {
            HashMap<String, String> testsOutput = mavenTestsService.executeAllTestsMutantSchemata(projectPathWrapper);

            mr.setAsSuccess();
            mr.setData(testsOutput);
            return ResponseEntity.status(HttpStatus.OK).body(mr);
        } catch (IOException e) {
            mr.setAsError("File Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mr);
        } catch (InterruptedException e) {
            mr.setAsError("Error running tests");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mr);
        } catch (ParseException e) {
            mr.setAsError("Error reading json file");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mr);
        }
    }

}
