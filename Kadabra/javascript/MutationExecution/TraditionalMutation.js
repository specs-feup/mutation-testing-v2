laraImport("lara.Io");
laraImport("lara.Strings");
laraImport("weaver.Query");
laraImport("weaver.Script");
laraImport("kadabra.KadabraNodes");
laraImport("MutationOperators.*");
laraImport("MutatorList");
laraImport("Decomposition");

const outputPath = laraArgs.outputPath;
const filePath = laraArgs.filePath;
const projectPath = laraArgs.projectPath.trim();
const debugMessages = laraArgs.debugMessages;
const fileName = filePath.substring(
  filePath.lastIndexOf(Io.getSeparator()) + 1
);
const projectExecutionName = laraArgs.projectExecutionName;

main();

function main() {
  //Shows aditional prints
  if (debugMessages) {
    setDebug(true);
  }

  //If no mutatans were selected
  if (mutatorList.length === 0) {
    println("No mutators selected");
    return;
  }

  changeVarDeclarations();
  println("Traditional Mutation");

  let output = {};

  //Goes to each node and stores the mutatation point
  println("Going through AST for file " + fileName);
  runTreeAndGetMutantsTraditionaly();

  //Goes to each stored mutation point and applies the mutation
  println("Generating Mutants for file " + fileName);
  output = applyTraditionalMutation();

  Script.setOutput({ output });
}

function runTreeAndGetMutantsTraditionaly() {
  for (var $jp of Query.root().descendants) {
    var $call = $jp.ancestor("call");

    // Ignore nodes that are children of $call with the name <init>
    if ($call !== undefined && $call.name === "<init>") continue;

    // Ignore nodes inside variable declarations
    //if ($jp.ancestor("localVariable") !== undefined) {
    //  continue;
    //}

    for (mutator of mutatorList) {
      if (mutator.addJp($jp)) {
        debug(mutator);
      }
    }
  }
}

function applyTraditionalMutation() {
  let auxOutputStr = [];
  for (mutator of mutatorList) {
    while (mutator.hasMutations()) {
      let auxLine = mutator.getMutationPoint().line;

      //Aplies the mutation
      mutator.mutate();

      //Saves to a file
      let path = saveFile(mutator.getName());

      auxOutputStr.push({
        mutantId: path,
        mutantion: mutator.toJson(),
        mutationLine: auxLine,
        filePath: Io.getRelativePath(filePath, projectPath),
      });
    }
  }
  for (i in auxOutputStr) {
    println(JSON.stringify(auxOutputStr[i]));
  }
  //print(auxOutputStr);
  return JSON.stringify(auxOutputStr);
}

function saveFile(mutatorName) {
  let relativePath = Io.getRelativePath(filePath, projectPath);

  let aux =
    Io.getSeparator() +
    mutatorName +
    "_" +
    fileName.replace(".java", "") +
    "_" +
    Strings.uuid();

  let newFolder = outputPath + Io.getSeparator() + projectExecutionName + aux;

  Io.copyFolder(projectPath, newFolder, true);

  Io.writeFile(
    newFolder +
      Io.getSeparator() +
      relativePath.replace("/", Io.getSeparator()),
    Query.root().code
  );

  return aux;
}
