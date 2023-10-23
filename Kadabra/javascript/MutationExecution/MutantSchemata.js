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
  
  
  if (MutatorList.getMutators().length === 0) {    
    println("No mutators selected");
    return;
  }

  
  var filesJp = Query.search("file").get();
  
  var files = filesJp.map(file => file.name).join();
  println("Applying mutant schemata to " + files)
  
  filesJp.forEach(file => patchFile(file));

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
    if ($call !== undefined && $call.name === "<init>") continue;

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

            // If case, get corresponding switch
            if(mutated.instanceOf("case")) {
              mutated = mutated.ancestor("switch");
              if(mutated === undefined) {
                throw "Could not get corresponding 'switch' of case '"+mutated+"'";
              }
            }

            println("Mutated: " + mutated.code);
            var tryStmt = mutated.ancestor("try");
            println("TryStmt: " + tryStmt);
            if(tryStmt !== undefined) {
              mutated = tryStmt;
            }
        }

        const srcCode = getStatementCode(mutated);

        //print(mutator.toJson());

        if (needElseIf) {
          //println("MUT: NEED ELSE IF")
          if (mutationPoints > 1) {
            //println("MUTATION POINTS GREATER THAN 1: " + mutationPoints)
            if (firstTime) {
              //println("MUT: FIRST TIME")
              mutated.insertBefore(
                'if("' +
                  mutantId +
                  '".equals(MUID_STATIC)){\n' +
                  srcCode +
                  "\n}"
                  //";\n}"                  
              );

              firstTime = false;
            } else {
              //println("MUT: OTHER TIME")
              mutated.insertBefore(
                'else if("' +
                  mutantId +
                  '".equals(MUID_STATIC)){\n' +
                  srcCode +
                  //";\n}"
                  "\n}"                  
              );
            }
            mutationPoints--;
          } else {
            //println("MUTATION POINTS EQUAL TO 1: " + mutationPoints + " - ELSE INSERTED")            
            mutated.insertBefore(
              'else if("' +
                mutantId +
                '".equals(MUID_STATIC)){\n' +
                srcCode +
                //";\n}else{\n\t"
                "\n}else{\n\t"                
            );
            mutated.insertAfter("}");
          }
        } else {
          //println("MUT: DO NOT NEED ELSE IF - ELSE INSERTED")

          mutated.insertBefore(
            'if("' +
              mutantId +
              '".equals(MUID_STATIC)){\n' +
              srcCode +
              //";\n}else{\n\t"
              "\n}else{\n\t"              
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
      "Could not find an insertion point for MUID in class "+mainClass.name+", found types: " + types
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
      'static final String MUID_STATIC = System.getProperty("MUID");'
    );    

    return;
  }

  const auxFunction = `
  public static String getMUID(){ \
  String propertyValue = null; \
  try { \
  java.lang.Process process = Runtime.getRuntime().exec("getprop MUID"); \ 
  InputStream inputStream = process.getInputStream(); \
  BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); \
  propertyValue = reader.readLine();\
  reader.close();\
  inputStream.close();\
  } catch (IOException e) {\
  Log.e("ERROR", String.valueOf(e));\
  }\
  return propertyValue;\
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

  insertPoint.insertBefore(
    'static final String MUID_STATIC = getMUID();'
  );   

}




function getStatementCode(mutated) {

  let srcCode = mutated.srcCode;

  //println("MUTATED BEFORE:\n" + srcCode)

  // Determine if needs ';'
  if(needsSemiColon(mutated)) {
    srcCode = srcCode + ";";
  }

  //println("MUTATED AFTER:\n" + srcCode)
  return srcCode;
}

function needsSemiColon(mutated) {

  if(mutated.instanceOf("if") || mutated.instanceOf("loop") || mutated.instanceOf("try") || mutated.instanceOf("callStatement")) {
    //println("NOT ADDING ; ->  " + mutated.joinPointType);
    return false;
  }

  //println("ADDING ; ->  " + mutated.joinPointType);
  return true;
}

/**
 * Specific patches for some of the tests.
 * 
 * @param {file} file 
 */
function patchFile(file) {

  if(file.name === "BattleNetImporter.java") {
      // _key must not be final, otherwise fully qualified names will not work
    Query.search("field", "_key").first().removeModifier("final");
  }
}