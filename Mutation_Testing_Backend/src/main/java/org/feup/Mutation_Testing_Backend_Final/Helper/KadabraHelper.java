package org.feup.Mutation_Testing_Backend_Final.Helper;

import org.feup.Mutation_Testing_Backend_Final.Model.Project.Project;
import org.json.simple.JSONObject;
import org.lara.interpreter.joptions.config.interpreter.LaraiKeys;
import org.lara.interpreter.joptions.config.interpreter.VerboseLevel;
import org.lara.interpreter.joptions.keys.FileList;
import org.suikasoft.jOptions.Interfaces.DataStore;
import pt.up.fe.specs.util.utilities.StringList;
import weaver.gui.KadabraLauncher;
import weaver.options.JavaWeaverKeys;

import java.io.File;
import java.util.List;

public class KadabraHelper {

    public static boolean callKadabra(String projectPath, String projectFolderToIgnore, String includesFolder, String pathToKadabraEntryPoint, String outputPath, boolean traditionaMutation, List<String> operatorNameList, List<List<String>> operatorArgumentList, String projectExecutionName){
        DataStore data = DataStore.newInstance("Kadabra Options");

        data.put(LaraiKeys.LARA_FILE, new File(pathToKadabraEntryPoint));
        data.put(LaraiKeys.VERBOSE, VerboseLevel.warnings);
        data.set(LaraiKeys.TRACE_MODE);
        //data.set(JavaWeaverKeys.FULLY_QUALIFIED_NAMES);
        data.set(LaraiKeys.DEBUG_MODE);
        //data.set(JavaWeaverKeys.NO_CLASSPATH);
        //data.set(JavaWeaverKeys.INCLUDE_DIRS, FileList.newInstance(new File()))
        data.set(JavaWeaverKeys.WRITE_CODE, false);
        data.set(JavaWeaverKeys.FORMAT, true);
        data.set(LaraiKeys.EXTERNAL_DEPENDENCIES,
                StringList.newInstance(
                        "https://github.com/specs-feup/lara-framework.git?folder=experimental/SourceAction",
                        "https://github.com/specs-feup/lara-framework.git?folder=experimental/Mutation"));
        data.put(LaraiKeys.INCLUDES_FOLDER, FileList.newInstance(new File(includesFolder)));

        JSONObject javascriptArguments = new JSONObject();
        javascriptArguments.put("outputPath", outputPath);
        javascriptArguments.put("traditionalMutation", traditionaMutation);
        javascriptArguments.put("folderToIgnore", projectFolderToIgnore);
        javascriptArguments.put("projectPath", projectPath);
        javascriptArguments.put("debugMessages", false);
        javascriptArguments.put("operatorNameList", operatorNameList);
        javascriptArguments.put("operatorArgumentList", operatorArgumentList);
        javascriptArguments.put("projectExecutionName", projectExecutionName);
        javascriptArguments.put("includesFolder", includesFolder);

        data.put(LaraiKeys.ASPECT_ARGS, javascriptArguments.toJSONString());

        return KadabraLauncher.execute(data);
    }

}
