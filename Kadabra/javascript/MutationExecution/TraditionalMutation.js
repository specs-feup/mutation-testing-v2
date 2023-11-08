laraImport("lara.Io");
laraImport("lara.Strings");
laraImport("weaver.Query");
laraImport("weaver.Script");
laraImport("kadabra.KadabraNodes");
laraImport("MutationOperators.*");
laraImport("MutatorList");
laraImport("Decomposition");
laraImport("MutatorUtils");

const outputPath = laraArgs.outputPath;
const filePath = laraArgs.filePath;
const projectPath = laraArgs.projectPath.trim();
const debugMessages = laraArgs.debugMessages;
const fileName = filePath.substring(
  filePath.lastIndexOf(Io.getSeparator()) + 1
);
const projectExecutionName = laraArgs.projectExecutionName;
let counter = 0;

main();

function main() {
  // Initialize counter
  counter = 0;

  //Shows aditional prints
  if (debugMessages) {
    setDebug(true);
  }

  //If no mutatans were selected
  if (MutatorList.getMutators().length === 0) {
    println("No mutators selected");
    return;
  }

  //changeVarDeclarations();
  println("Traditional Mutation");

  let output = {};

  // Apply mutations traditionally
  println("Generating Mutants for file " + fileName);
  output = applyTraditionalMutation();

  /*
  //Goes to each node and stores the mutatation point
  println("Going through AST for file " + fileName);
  runTreeAndGetMutantsTraditionaly();

  //Goes to each stored mutation point and applies the mutation
  println("Generating Mutants for file " + fileName);
  output = applyTraditionalMutation();
  */
  Script.setOutput({ output });
}

function applyTraditionalMutation() {
  let auxOutputStr = [];

  for (var $jp of Query.root().descendants) {

    var $call = $jp.ancestor("call");

    // Ignore nodes that are children of $call with the name <init>
    if ($call !== undefined && $call.name === "<init>") {
      continue;
    }

    const mutatorList = MutatorList.getMutators();
    for (mutator of mutatorList) {

      if (mutator.addJp($jp)) {
        debug(mutator);
      }

      while (mutator.hasMutations()) {
        const mutationId = nextMutationId(mutator.getName());

        let auxLine = mutator.getMutationPoint().line;

        // Insert comment signaling the mutation
        mutator.getMutationPoint().insertBefore("// " + mutationId);

        //Aplies the mutation
        mutator.mutate();

        //Saves to a file
        let path = saveFile(mutationId);

        auxOutputStr.push({
          mutantId: mutationId,
          mutantion: mutator.toJson(),
          mutationLine: auxLine,
          filePath: Io.getRelativePath(filePath, projectPath),
        });


      }
    }
  }

  return JSON.stringify(auxOutputStr);
}


function nextMutationId(mutatorName) {
  const id = 
    //Io.getSeparator() +
    mutatorName +
    "_" +
    fileName.replace(".java", "") +
    "_" +
    counter;

  // Update counter
  counter += 1;

  return id;
}

function saveFile(mutationId) {
  let relativePath = Io.getRelativePath(filePath, projectPath);

  let aux = Io.getSeparator() + mutationId;

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
