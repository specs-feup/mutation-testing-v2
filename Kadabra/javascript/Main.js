laraImport("lara.Io");
laraImport("lara.Strings");
laraImport("weaver.Query");
laraImport("weaver.WeaverOptions");
laraImport("Arguments");


var contextFolder = WeaverOptions.getData().getContextFolder();


// Support for paths relative to the configuration file 
const projectPaths = parsePathList(contextFolder, laraArgs.projectPath);

println("Project paths: " + projectPaths)

const outputPaths = parsePathList(contextFolder, laraArgs.outputPath);

println("Output Paths: " + laraArgs.outputPath)
if (outputPaths.length !== projectPaths.length) {
  throw "Project paths length (" + projectPaths.length + ") must be the same as project outputs paths (" + outputPaths.length + ")";
}

//const includesFolders = parsePathList(contextFolder, laraArgs.includesFolder);
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
const fullyQualifiedNames = laraArgs.fullyQualifiedNames;
const patch = laraArgs.patch;

main();

/**
 * Transforms a path into an absolute path.
 * If it is a relative path, uses the given base folder as the base path.
 * 
 * @param {java.io.File} baseFolder  
 * @param {String} path 
 * @return {String} an absolute path to the given path 
 */
function toAbsolutePath(baseFolder, path) {
  let file = Io.getPath(path);

  if (file.isAbsolute()) {
    return file.getAbsolutePath();
  }

  return Io.getPath(baseFolder, path).getAbsolutePath();
}

function parsePathList(baseFolder, paths) {
  const pathsArray = paths.split(";");

  const finalPaths = [];

  for (const path of pathsArray) {
    // Convert to absolute path
    const absPath = toAbsolutePath(baseFolder, path);

    // If exists, add to path list
    //if(Io.getPath(absPath).exists()) {
    finalPaths.push(absPath);
    //} else {
    //  println("Could not find path '"+path+"'");
    //}
  }

  return finalPaths;

}

function main() {
  //Shows aditional prints
  if (debugMessages) {
    setDebug(true);
  }


  //makes the project copy if it's not being used traditional mutation
  if (!traditionalMutation) {
    for (let i = 0; i < projectPaths.length; i++) {
      const projectPath = projectPaths[i];
      const outputPath = outputPaths[i];
      println("Copying " + projectPath)
      Io.copyFolder(
        projectPath,
        outputPath + Io.getSeparator() + projectExecutionName,
        true
      );
    }
  }


  const args_final = [];

  // Get only java files without the test files
  const filesToUsePerProject = getFilesToUse();
  println("Files to use: " + Object.values(filesToUsePerProject));

  // Count total files
  let totalFiles = 0;
  for (let i = 0; i < projectPaths.length; i++) {
    const projectPath = projectPaths[i];
    const filesToUse = filesToUsePerProject[projectPath];
    totalFiles += filesToUse.length;
  } 

  let currentFileIndex = 0;

  for (let i = 0; i < projectPaths.length; i++) {
    const projectPath = projectPaths[i];
    const outputPath = outputPaths[i];
    const filesToUse = filesToUsePerProject[projectPath];
    //filesToUse = getFilesToUse();

    //Creates the arguments for each kadabra parallel execution
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
        baseIndex: currentFileIndex,
        totalFiles: totalFiles,
        patch: patch,
      };

      currentFileIndex += 1;


      let args_kadabra = new Arguments(
        (outputPath + Io.getSeparator() + projectExecutionName).trim(),
        JSON.stringify(args),
        filesToUse[i],
        traditionalMutation,
        includesFolder,
        classpath,
        useIncompleteClassPath,
        isAndroid,
        fullyQualifiedNames
      ).getList();

      args_final.push(args_kadabra);
    }
  }
  //Kadabra Parallel execution
  //let result = Weaver.runParallel(args_final, args_final.length);
  let result = Weaver.runParallel(args_final, 1);
  //let result = Weaver.runParallel(args_final, 16);    

  //Writes the output formated to a file
  writeExecutionInfo(result, args_final);
}

function getFilesToUse() {
  const filesToUsePerProject = {};
  for (const projectPath of projectPaths) {

    filesToUsePerProject[projectPath] = [];
    //const filesToUse = filesToUsePerProject[projectPath];
    // let filesToUse = [];
    // filesToUsePerProject[projectPath] = filesToUse;

    //Checks what files to use
    let allJavaFiles = Io.getFiles(projectPath, "*.java", true);
    let javaFilesToRemove = Io.getFiles(folderToIgnore, "*.java", true);
    //println("AllJavaFiles: " + allJavaFiles);
    //println("Folder to Ignore: " + folderToIgnore);
    //println("javaFilesToRemove: " + javaFilesToRemove);

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
            //filesToUse.push(allJavaFiles[i]);
            filesToUsePerProject[projectPath].push(allJavaFiles[i]);
          }
        }
      }
    } else {
      //filesToUse = allJavaFiles;
      filesToUsePerProject[projectPath] = allJavaFiles;
    }

    if (folderToIgnoreAndroid != null && folderToIgnoreAndroid != "") {
      let javaFilesToRemove = Io.getFiles(folderToIgnoreAndroid, "*.java", true);
      let filesToUseFinal = [];

      for (i in filesToUsePerProject[projectPath]) {
        for (j in javaFilesToRemove) {
          if (filesToUsePerProject[projectPath][i].equals(javaFilesToRemove[j])) {
            break;
          }
          if (j == javaFilesToRemove.length - 1 && !j.includes("build")) {
            filesToUseFinal.push(filesToUsePerProject[projectPath][i]);
            //filesToUseFinal.push(filesToUse[i]);
          }
        }
      }

      filesToUsePerProject[projectPath] = filesToUseFinal;
      //return filesToUseFinal;
    }
  }
  //return filesToUse;
  return filesToUsePerProject;
}

function writeExecutionInfo(result, args_final) {
  let fileData = [];

  for (i in result) {
    if (result[i]["output"] != "[]") {
      try {
        let listaAux = JSON.parse(result[i]["output"]);
        for (j in listaAux) {
          fileData.push(listaAux[j]);
        }
      } catch (error) {
        println("Exception while parsing output result with index " + i + ": " + error);
        //println('Contents of result[i]["output"]:');
        //printlnObject(result[i]["output"]);

        fileData.push({ "error": error.message, "args": args_final[i] });
      }
    }
  }

  Io.writeFile(
    outputPaths[0] +
    Io.getSeparator() +
    projectExecutionName +
    Io.getSeparator() +
    "MutationInfo.json",
    JSON.stringify(fileData)
  );
}
