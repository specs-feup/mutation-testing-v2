laraImport("lara.Io");
laraImport("lara.Strings");
laraImport("weaver.Query");
laraImport("weaver.Script");
laraImport("kadabra.KadabraNodes");
laraImport("MutationOperators.*");
laraImport("MutatorList");
laraImport("Decomposition");
laraImport("MutatorUtils");
laraImport("IdNumberGenerator");

// ToDo: This should be a class with instance variables, not a script with global variables
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
const baseIndex = laraArgs.baseIndex;
const totalFiles = laraArgs.totalFiles;
const patch = laraArgs.patch;

main();

function main() {
  //Shows aditional prints
  if (debugMessages) {
    setDebug(true);
  }

  //If no mutatans were selected
  
  if (MutatorList.getMutators().length === 0) {    
    println("No mutators selected");
    return;
  }

  var filesJp = Query.search("file").get();
  
  var files = filesJp.map((file) => file.name).join();
  println("Applying mutant schemata to " + files);
  
  // Check if patch needs to be applied
  if(patch !== undefined) {
    println("Applying patch '"+patch+"'")
    // Import it. This should expose a global function "patchFile" that accepts a file
    laraImport(patch);
    filesJp.forEach((file) => patchFile(file));
  }



  Decomposition.changeVarDeclarations();
  //println("Mutant Schemata");

  let output = runTreeAndApplyMetaMutant();

  //print("Output" + output);
  Script.setOutput({ output });
}

function runTreeAndApplyMetaMutantNew() {
  // Two phases, first collect mutations associated with each point,
  // then mutate on that point
  // Map jps to list of mutations 
  // On second phase, walk the tree again, apply mutations over each point, one at a time
  // NOT IMPLEMENTED YET
  // Requires another architecture, where Mutator returns a list of Mutations
}

function runTreeAndApplyMetaMutant() {
  var mutantList = [];
  let mutantCounter = 0;
  const idNumberGenerator = new IdNumberGenerator(baseIndex, totalFiles);

  // Add MUID_STATIC variable to all files
  for (var $jp of Query.search("file")) {
    //println("FILE: " + $jp.name);
    //println("PATH: " + $jp.path);
    this.addMuidStatic($jp);
  }

  var descendants = Query.root().descendants;
  //descendants.reverse();

  for (var $jp of descendants) {
    // Add MUID_STATIC variable to all files
    //if ($jp.instanceOf("file")) {
    //  this.addMuidStatic($jp);
    //}

    var $call = $jp.ancestor("call");

    // Ignore nodes that are children of $call with the name <init>
    if ($call !== undefined && $call.name === "<init>") {
      continue;
    }

    let mutationPoints = 0;
    let needElseIf = false;
    let firstTime = true;
    const mutatorList = MutatorList.getMutators();

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
        // Generate the new mutant ID
        let mutantId = MutatorUtils.buildMutantId(fileName, mutantCounter, mutator);
        const mutantIdNumber = idNumberGenerator.next();

/*
        mutator.getName() +
          "_" +
          fileName.replace(".java", "") +
          "_" +
          mutantCounter;
*/          

          mutantCounter++;

        const line = mutator.getMutationPoint().line;
        if(line === undefined) {
          println("- Mutation point of type '"+mutator.getMutationPoint().joinPointType+"' with no line defined");
        }

        mutantList.push({
          mutantId: mutantId,
          mutantIdNumber: mutantIdNumber,
          mutantion: mutator.toJson(),
          mutationLine: line,
          filePath: Io.getRelativePath(filePath, projectPath),
        });

        const mutationPoint = mutator.getMutationPoint();
        //println("SRC CODE BEFORE:\n" + mutationPoint.ancestor("statement"))

        // Mutate
        println("- Applying mutator '"+mutator.getName()+"' ("+mutantId+")");
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

          if(mutated === undefined && mutator.getMutationPoint() !== undefined) {
            throw "Could not get a statement out of the mutation point, probably is being applied to a point that is not inside a statement or that is not a statement.";
          }

            // If case, get corresponding switch
            if(mutated.instanceOf("case")) {
              mutated = mutated.ancestor("switch");
              if(mutated === undefined) {
              throw (
                "Could not get corresponding 'switch' of case '" + mutated + "'"
              );
              }
            }

            println("Mutated: " + mutated.code);
            var tryStmt = mutated.ancestor("try");
            //println("TryStmt: " + tryStmt);
            if(tryStmt !== undefined) {
              mutated = tryStmt;
            }
        }

        const srcCode = MutatorUtils.getStatementCode(mutated);
        const breakCode = MutatorUtils.isReturningStmt(mutated) ? '' : 'break;\n';
        //println("SRC CODE AFTER MUTATION:\n" + mutationPoint.ancestor("statement"))
        //print(mutator.toJson());

        if (needElseIf) {
          //println("MUT: NEED ELSE IF")
          if (mutationPoints > 1) {
            //println("MUTATION POINTS GREATER THAN 1: " + mutationPoints)
            if (firstTime) {
              //println("MUT: FIRST TIME")
              mutated.insertBefore(
                'switch(MUID_STATIC) {\n' +
                '// '+ mutantId + "\n" +
                'case ' + mutantIdNumber + ': {\n' +
                srcCode + "\n" +
                breakCode +
                '}\n'
              );


              firstTime = false;
            } else {
              //println("MUT: OTHER TIME")
              mutated.insertBefore(
                '// '+ mutantId + "\n" +
                'case ' + mutantIdNumber + ': {\n' +
                srcCode + "\n" +
                breakCode +
                '}\n'
              );

            }
            mutationPoints--;
          } else {
            //println("MUTATION POINTS EQUAL TO 1: " + mutationPoints + " - ELSE INSERTED")            
            mutated.insertBefore(
              '// '+ mutantId + "\n" +
              'case ' + mutantIdNumber + ': {\n' +
              srcCode + "\n" +
              breakCode +
              '}\n' +
              'default: {\n'
            );

            mutated.insertAfter(
              breakCode + 
              '}\n' +
              '}\n'
              );

          }
        } else {
          //println("MUT: DO NOT NEED ELSE IF - ELSE INSERTED")

          mutated.insertBefore(
            'switch(MUID_STATIC) {\n' +
            '// '+ mutantId + "\n" +
            'case ' + mutantIdNumber + ': {\n' +
            srcCode + "\n" +
            breakCode +
            '}\n' +
            'default: {\n'
          );


          mutated.insertAfter(
            breakCode + 
            '}\n' +
            '}\n'
            );

        }

        mutator.restore();

        //println("SRC CODE AFTER RESTORE:\n" + mutationPoint.ancestor("statement"))
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


  const children = mainClass.children;
  if (children.length === 0) {
    println("Could not find any element inside class " + mainClass.name);
    return;
  }

  let insertPoint = undefined;
  const types = [];
  for (const child of children) {
    types.push(child.joinPointType);

    if(child.instanceOf("enumValue")) {
      continue;
    }

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
      "Could not find an insertion point for MUID in class " +
        mainClass.name +
        ", found types: " +
        types
    );
    return;
  }

  // Declare MUID_STATIC
  insertMuidStaticCode(mainClass, insertPoint, isAndroid);
}

function insertMuidStaticCode(mainClass, insertPoint, isAndroid) {
  // Not Android, using Java properties
  if(!isAndroid) {
    // Declare MUID_STATIC
    insertPoint.insertBefore(
      'static final String ORIGINAL_MUID_STATIC = System.getProperty("MUID");\n' +
      'static final int MUID_STATIC = ORIGINAL_MUID_STATIC != null ? Integer.parseInt(ORIGINAL_MUID_STATIC) : -1;'      
    );    

    return;
  }

  const auxFunction = `
  public static int getMUID(){ \
  String propertyValue = "-1"; \
  try { \
  java.lang.Process process = Runtime.getRuntime().exec("getprop debug.MUID"); \ 
  InputStream inputStream = process.getInputStream(); \
  BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); \
  propertyValue = reader.readLine();\
  reader.close();\
  inputStream.close();\
  } catch (IOException e) {\
  Log.e("ERROR", String.valueOf(e));\
  }\
  return Integer.parseInt(propertyValue);\
  }`;

  const imports = `
  import java.io.BufferedReader;\n 
  import java.io.IOException;\n
  import java.io.InputStream;\n
  import java.io.InputStreamReader;\n
  import android.util.Log;
  `;

  mainClass.insertBefore(imports);
  mainClass.insertMethod(auxFunction);

  insertPoint.insertBefore("static final int MUID_STATIC = getMUID();");
}

/*
function getStatementCode(mutated) {
  let srcCode = mutated.srcCode;

  // Determine if needs ';'
  if(MutatorUtils.needsSemiColon(mutated)) {
    srcCode = srcCode + ";";
  }

  return srcCode;
}
*/


/**
 * Specific patches for some of the tests.
 * 
 * @param {file} file 
 */
/*
function patchFile(file) {
  if(file.name === "BattleNetImporter.java") {
      // _key must not be final, otherwise fully qualified names will not work
    Query.search("field", "_key").first().removeModifier("final");
  }

  if(file.name === "EditEntryActivity.java") {
    var call = Query.searchFrom(file, "call", "showTextInputDialog").first();
    call.setArgument(
      KadabraNodes.snippetExpr(
        "com.beemdevelopment.aegis.ui.EditEntryActivity.this"
      ),
      0
    );
  }

  if(file.name === "MainActivity.java") {
    var call = Query.searchFrom(file, "call", "showDeleteEntriesDialog").first();
    call.setArgument(KadabraNodes.snippetExpr("com.beemdevelopment.aegis.ui.MainActivity.this"), 0);
  }

  
}
*/
