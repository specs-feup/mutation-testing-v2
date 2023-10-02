laraImport("lara.Io");
laraImport("lara.Strings");
laraImport("weaver.Query");
laraImport("weaver.WeaverOptions");
laraImport("Arguments");


var contextFolder = WeaverOptions.getData().getContextFolder();


// Support for paths relative to the configuration file 
const projectPath = Io.getPath(contextFolder, laraArgs.projectPath).getAbsolutePath();
const outputPath = Io.getPath(contextFolder, laraArgs.outputPath).getAbsolutePath();
const includesFolder = Io.getPath(contextFolder, laraArgs.includesFolder).getAbsolutePath();

const traditionalMutation = laraArgs.traditionalMutation;
const debugMessages = laraArgs.debugMessages;
const folderToIgnore = laraArgs.folderToIgnore;
const folderToIgnoreAndroid = laraArgs.folderToIgnoreAndroid;
const operatorNameList = laraArgs.operatorNameList;
const operatorArgumentList = laraArgs.operatorArgumentList;
const projectExecutionName = laraArgs.projectExecutionName;
const isAndroid = laraArgs.isAndroid;
const classpath = laraArgs.classpath;
const useIncompleteClassPath = laraArgs.useIncompleteClassPath;
const mutationType = laraArgs.mutationType;

main();

function main() {
  //Shows aditional prints
  if (debugMessages) {
    setDebug(true);
  }

  // Get only java files without the test files
  filesToUse = getFilesToUse();
  println("Files to use: " + filesToUse);

  //makes the project copy if it's not being used traditional mutation
  if (!traditionalMutation) {
    Io.copyFolder(
      projectPath,
      outputPath + Io.getSeparator() + projectExecutionName,
      true
    );
  }

  //Creates the arguments for each kadabra parallel execution
  args_final = [];
  for (i in filesToUse) {
    args = {
      outputPath: outputPath.trim(),
      filePath: filesToUse[i].toString(),
      projectPath: projectPath,
      debugMessages: debugMessages,
      operatorNameList: operatorNameList,
      operatorArgumentList: operatorArgumentList,
      projectExecutionName: projectExecutionName,
      isAndroid: laraArgs.isAndroid,
      mutationType: laraArgs.mutationType,
    };

    let args_kadabra = new Arguments(
      (outputPath + Io.getSeparator() + projectExecutionName).trim(),
      JSON.stringify(args),
      filesToUse[i],
      traditionalMutation,
      includesFolder,
      classpath,
      useIncompleteClassPath,
      isAndroid
    ).getList();

    args_final.push(args_kadabra);
  }

  //Kadabra Parallel execution
  let result = Weaver.runParallel(args_final, args_final.length);

  //Writes the output formated to a file
  writeExecutionInfo(result);
}

function getFilesToUse() {
  let filesToUse = [];

  //Checks what files to use
  let allJavaFiles = Io.getFiles(projectPath, "*.java", true);
  let javaFilesToRemove = Io.getFiles(folderToIgnore, "*.java", true);
  println("AllJavaFiles: " + allJavaFiles);
  println("Folder yo Ignore: " + folderToIgnore);
  println("javaFilesToRemove: " + javaFilesToRemove);

  if (
    folderToIgnore != null &&
    folderToIgnore != "" &&
    folderToIgnore.replace(projectPath, "") != ""
  ) {
    for (i in allJavaFiles) {
      for (j in javaFilesToRemove) {
        if (allJavaFiles[i].equals(javaFilesToRemove[j])) {
          break;
        }
        if (j == javaFilesToRemove.length - 1 && !j.includes("build")) {
          filesToUse.push(allJavaFiles[i]);
        }
      }
    }
  } else {
    filesToUse = allJavaFiles;
  }

  if (folderToIgnoreAndroid != null && folderToIgnoreAndroid != "") {
    let javaFilesToRemove = Io.getFiles(folderToIgnoreAndroid, "*.java", true);
    let filesToUseFinal = [];

    for (i in filesToUse) {
      for (j in javaFilesToRemove) {
        if (filesToUse[i].equals(javaFilesToRemove[j])) {
          break;
        }
        if (j == javaFilesToRemove.length - 1 && !j.includes("build")) {
          filesToUseFinal.push(filesToUse[i]);
        }
      }
    }

    return filesToUseFinal;
  }

  return filesToUse;
}

function writeExecutionInfo(result) {
  let fileData = [];

  for (i in result) {
    if (result[i]["output"] != "[]") {
      try {
        let listaAux = JSON.parse(result[i]["output"]);
        for (j in listaAux) {
          fileData.push(listaAux[j]);
        }
      } catch (error) {
        println(result[i]);
        println(error);
      }
    }
  }

  Io.writeFile(
    outputPath +
      Io.getSeparator() +
      projectExecutionName +
      Io.getSeparator() +
      "MutationInfo.json",
    JSON.stringify(fileData)
  );
}
