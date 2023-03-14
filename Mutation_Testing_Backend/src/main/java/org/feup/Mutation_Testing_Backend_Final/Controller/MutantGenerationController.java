package org.feup.Mutation_Testing_Backend_Final.Controller;

import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.KadabraWrapper;
import org.feup.Mutation_Testing_Backend_Final.Service.MutantGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/mutation")
public class MutantGenerationController {
    private final MutantGenerationService mutantGenerationService;
    @Autowired
    public MutantGenerationController(MutantGenerationService mutantGenerationService) {
        this.mutantGenerationService = mutantGenerationService;
    }

    @PostMapping("/generateProject")
    public void generateNewProject(@RequestBody KadabraWrapper kadabraWrapper){
        mutantGenerationService.generateNewProject(kadabraWrapper);
        return;
    }

    @PostMapping("/generateNewMutationFile")
    public void generateNewMutationFile(){
        return;
    }

    @PostMapping("/generatePorjectWithNewMutationFile")
    public void generatePorjectWithNewMutationFile(){

    }

}
