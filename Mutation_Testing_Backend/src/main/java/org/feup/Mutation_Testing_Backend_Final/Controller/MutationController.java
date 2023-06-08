package org.feup.Mutation_Testing_Backend_Final.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.feup.Mutation_Testing_Backend_Final.Dto.SimpleResponse;
import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.CreateMutantWrapper;
import org.feup.Mutation_Testing_Backend_Final.Service.MutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/mutate")
public class MutationController {
    private final MutationService mutationService;

    @Autowired
    public MutationController(MutationService mutationService) {
        this.mutationService = mutationService;
    }


    @PostMapping("/{projectVersionId}")
    @Operation(summary = "Create mutant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success creating mutated project",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SimpleResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No project Version Found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error in the data recieved",
                    content = @Content)})
    public ResponseEntity<SimpleResponse> createMutant(@PathVariable String projectVersionId, @RequestParam String mutantGenerationType, @RequestBody CreateMutantWrapper createMutantWrapper) {
        SimpleResponse sr = new SimpleResponse();

        try{
            sr = mutationService.mutateProjectVersion(projectVersionId, mutantGenerationType, createMutantWrapper);


            return ResponseEntity.status(HttpStatus.OK).body(sr);
        }catch (NumberFormatException e){
            sr.setAsError("Invalid Project Id");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }catch (IllegalArgumentException e){
            sr.setAsError("Invalid Test Execution Type");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
        }
    }

}
