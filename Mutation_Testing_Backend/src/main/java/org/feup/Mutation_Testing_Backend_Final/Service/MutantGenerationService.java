package org.feup.Mutation_Testing_Backend_Final.Service;

import org.feup.Mutation_Testing_Backend_Final.Dto.Wrapper.KadabraWrapper;
import org.feup.Mutation_Testing_Backend_Final.Repository.projectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import org.json.simple.JSONObject;
import org.lara.interpreter.joptions.config.interpreter.LaraiKeys;
import org.lara.interpreter.joptions.config.interpreter.VerboseLevel;
import org.lara.interpreter.joptions.keys.FileList;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.util.utilities.StringList;
import weaver.gui.KadabraLauncher;
import weaver.options.JavaWeaverKeys;*/

import java.io.File;

@Service
public class MutantGenerationService {
    public boolean generateNewProject(KadabraWrapper kadabraWrapper) {
        try {
            /*DataStore data = DataStore.newInstance("Kadabra Options");

            data.put(LaraiKeys.LARA_FILE, new File(kadabraWrapper.getFilePath()));
            data.put(LaraiKeys.VERBOSE, VerboseLevel.warnings);
            data.set(LaraiKeys.TRACE_MODE);
            data.set(JavaWeaverKeys.FULLY_QUALIFIED_NAMES);
            data.set(LaraiKeys.DEBUG_MODE);
            data.set(JavaWeaverKeys.NO_CLASSPATH);
            data.set(JavaWeaverKeys.WRITE_CODE, false);
            data.set(LaraiKeys.EXTERNAL_DEPENDENCIES,
                    StringList.newInstance(
                            "https://github.com/specs-feup/lara-framework.git?folder=experimental/SourceAction",
                            "https://github.com/specs-feup/lara-framework.git?folder=experimental/Mutation"));
            data.put(LaraiKeys.INCLUDES_FOLDER, FileList.newInstance(new File("C:\\Users\\david\\git\\mutation-testing\\MutatorV2\\javascript_src\\")));

            JSONObject javascriptArguments = new JSONObject();
            javascriptArguments.put("outputPath", kadabraWrapper.getOutputPath());
            javascriptArguments.put("traditionalMutation", kadabraWrapper.isTraditionalMutation());
            javascriptArguments.put("folderToIgnore", "C:\\Users\\david\\Desktop\\TestProject\\src\\test");
            javascriptArguments.put("projectPath", kadabraWrapper.getProjectPath());
            javascriptArguments.put("debugMessages", kadabraWrapper.isDebugMessages());

            data.put(LaraiKeys.ASPECT_ARGS, javascriptArguments.toJSONString());

            /*var replacer = new Replacer(SpecsIo.getResource("template.js"));
            replacer.replace("<IMPORT>", "");
            replacer.replace("<MUTATORS>", "new BinaryMutator(\"+\",\"*\"),\n" +
                    "\tnew BinaryMutator(\"+\",\"-\"),\n" +
                    "\tnew BinaryMutator(\"+\",\"/\"),\n" +
                    "\tnew BinaryMutator(\"+\",\"%\"),\n" +
                    "\tnew BinaryMutator(\"-\",\"+\"),\n" +
                    "\tnew BinaryMutator(\"-\",\"/\"),\n" +
                    "\tnew BinaryMutator(\"-\",\"*\"),\n" +
                    "\tnew BinaryMutator(\"-\",\"%\") ");
            SpecsIo.write(new File("javascript_src_2\\Mutators.js"), replacer.toString());

            return KadabraLauncher.execute(data);*/
            return true;
        } catch (Exception e) {
            // LOGG ER.error("Exception during Kadabra execution: " + e);
            System.out.print("Exception during Kadabra execution: " + e);
            e.printStackTrace();
            return false;
        }
    }
}
