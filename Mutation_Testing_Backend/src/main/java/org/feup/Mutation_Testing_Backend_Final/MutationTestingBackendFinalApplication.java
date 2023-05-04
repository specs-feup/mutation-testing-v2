package org.feup.Mutation_Testing_Backend_Final;

import org.feup.Mutation_Testing_Backend_Final.Model.Project.ProjectTestExecution;
import org.feup.Mutation_Testing_Backend_Final.Model.Test.TestUnit;
import org.json.simple.JSONObject;
import org.lara.interpreter.joptions.config.interpreter.LaraiKeys;
import org.lara.interpreter.joptions.config.interpreter.VerboseLevel;
import org.lara.interpreter.joptions.keys.FileList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.util.utilities.StringList;
import weaver.gui.KadabraLauncher;
import weaver.options.JavaWeaverKeys;

import java.io.File;


@SpringBootApplication
public class MutationTestingBackendFinalApplication {
	public static void main(String[] args) {
		SpringApplication.run(MutationTestingBackendFinalApplication.class, args);
	}

}
