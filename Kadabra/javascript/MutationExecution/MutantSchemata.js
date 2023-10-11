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
const operatorNameList = laraArgs.operatorNameList;
const projectExecutionName = laraArgs.projectExecutionName;
const isAndroid = laraArgs.isAndroid;

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
  //println("Mutant Schemata");

  let output = runTreeAndApplyMetaMutant();

  //print("Output" + output);
  Script.setOutput({ output });
}

function runTreeAndApplyMetaMutant() {
  var mutantList = [];

  // Add MUID_STATIC variable to all files
  for (var $jp of Query.search("file")) {
    //println("FILE: " + $jp.name);
    //println("PATH: " + $jp.path);
    this.addMuidStatic($jp);
  }

  for (var $jp of Query.root().descendants) {
    // Add MUID_STATIC variable to all files
    //if ($jp.instanceOf("file")) {
    //  this.addMuidStatic($jp);
    //}

    var $call = $jp.ancestor("call");

    // Ignore nodes that are children of $call with the name <init>
    if ($call !== undefined && $call.name === "<init>") continue;

    let mutationPoints = 0;
    let needElseIf = false;
    let firstTime = true;
    for (mutator of mutatorList) {
      if (mutator.addJp($jp)) {
        mutationPoints++;
        debug(mutator);
      }
    }

    if (mutationPoints >= 2) {
      needElseIf = true;
    }

    for (mutator of mutatorList) {
      while (mutator.hasMutations()) {
        let mutantId =
          mutator.getName() +
          "_" +
          fileName.replace(".java", "") +
          "_" +
          Strings.uuid();

        mutantList.push({
          mutantId: mutantId,
          mutantion: mutator.toJson(),
          mutationLine: mutator.getMutationPoint().line,
          filePath: Io.getRelativePath(filePath, projectPath),
        });

        // Mutate
        mutator.mutate();

        if (
          mutator.name === "NotSerializableOperatorMutator" ||
          mutator.name === "NonVoidCallMutator"
        ) {
          var mutated = mutator.getMutationPoint();
        } else {
          var mutated = mutator.getMutationPoint().isStatement
            ? mutator.getMutationPoint()
            : mutator.getMutationPoint().ancestor("statement");
        }

        //print(mutator.toJson());

        if (needElseIf) {
          if (mutationPoints > 1) {
            if (firstTime) {
              mutated.insertBefore(
                'if("' +
                  mutantId +
                  '".equals(MUID_STATIC)){\n' +
                  mutated.srcCode +
                  ";\n}"
              );

              firstTime = false;
            } else {
              mutated.insertBefore(
                'else if("' +
                  mutantId +
                  '".equals(MUID_STATIC)){\n' +
                  mutated.srcCode +
                  ";\n}"
              );
            }
            mutationPoints--;
          } else {
            mutated.insertBefore(
              'else if("' +
                mutantId +
                '".equals(MUID_STATIC)){\n' +
                mutated.srcCode +
                ";\n}else{\n\t"
            );
            mutated.insertAfter("}");
          }
        } else {
          mutated.insertBefore(
            'if("' +
              mutantId +
              '".equals(MUID_STATIC)){\n' +
              mutated.srcCode +
              ";\n}else{\n\t"
          );
          mutated.insertAfter("}");
        }

        mutator.restore();
      }
    }

    //println("APPLIED ALL MUTATIONS");
  }

  //Saves the file
  let relativePath = Io.getRelativePath(filePath, projectPath);

  let aux =
    outputPath +
    Io.getSeparator() +
    projectExecutionName +
    Io.getSeparator() +
    relativePath.replace("/", Io.getSeparator());

  Io.writeFile(aux, Query.root().srcCode);

  return JSON.stringify(mutantList);
}

function addMuidStatic($file) {
  println("Adding MUID_STATIC to file " + $file);
  const mainClass = $file.mainClass;

  if (mainClass === undefined) {
    println("Could not find main class, could not add MUID_STATIC");
    return;
  }


//    println(Query.root().ast);

  //println("Main class: " + mainClass);

  // Declare MUID_STATIC
  // Does not work, because inserts at the end of the class, and this is a problem if
  // used inside static blocks
  //mainClass.insertCode(
  //  'static final String MUID_STATIC = System.getProperty("MUID");'
  //);


  const children = mainClass.children;
  if (children.length === 0) {
    println("Could not find any element inside class " + mainClass.name);
    return;
  }

  let insertPoint = undefined;
  const types = [];
  for (const child of children) {
    types.push(child.joinPointType);
    if (
      child.instanceOf("declaration") ||
      //child.instanceOf("type") ||
      child.instanceOf("executable")
    ) {
      insertPoint = child;
      break;
    }
  }

  if (insertPoint === undefined) {
    println(
      "Could not find an insertion point for MUID in class "+mainClass.name+", found types: " + types
    );
    return;
  }

  // Declare MUID_STATIC
  insertPoint.insertBefore(
    'static final String MUID_STATIC = System.getProperty("MUID");'
  );

}
