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
const mutationType = laraArgs.mutationType;

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
  //println("Mutant Schemata Android");

  let output = runTreeAndApplyMetaMutant();

  if (output.length > 0) {
    //print("Output" + output);
    Script.setOutput({ output });
  } else {
    Script.setOutput({});
  }
}

function runTreeAndApplyMetaMutant() {
  if (mutationType === "MUTANTSCHEMATA") {
    const auxFunction = `
    public static String getMUID(){ \
    String propertyValue = null; \
    try { \
    Process process = Runtime.getRuntime().exec("getprop MUID"); \ 
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

    for (var $jp of Query.search("class")) {
      $jp.insertBefore(imports);
      $jp.insertMethod(auxFunction);
      break;
    }
  } else if (isAndroid) {
    const imports = `
    import com.beemdevelopment.aegis.BuildConfig;\n 
    `;

    for (var $jp of Query.search("class")) {
      $jp.insertBefore(imports);
      break;
    }
  }

  var mutantList = [];
  for (var $jp of Query.root().descendants) {
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

        if (mutator.isAndroidSpecific() && mutationType === "MUTANTSCHEMATA") {
          if (needElseIf) {
            if (mutationPoints > 1) {
              if (firstTime) {
                mutated.insertBefore(
                  'if(getMUID().equals("' +
                    mutantId +
                    '")){\n' +
                    mutated.srcCode +
                    ";\n}"
                );

                firstTime = false;
              } else {
                mutated.insertBefore(
                  'else if(getMUID().equals("' +
                    mutantId +
                    '")){\n' +
                    mutated.srcCode +
                    ";\n}"
                );
              }
              mutationPoints--;
            } else {
              mutated.insertBefore(
                'else if (getMUID().equals("' +
                  mutantId +
                  '")){\n' +
                  mutated.srcCode +
                  ";\n}else{\n\t"
              );
              mutated.insertAfter("}");
            }
          } else {
            mutated.insertBefore(
              'if (getMUID().equals("' +
                mutantId +
                '")){\n' +
                mutated.srcCode +
                ";\n}else{\n\t"
            );
            mutated.insertAfter("}");
          }
        } else {
          if (needElseIf) {
            if (mutationPoints > 1) {
              if (firstTime) {
                mutated.insertBefore(
                  'if(BuildConfig.MUID != null && BuildConfig.MUID.equals("' +
                    mutantId +
                    '")){\n' +
                    mutated.srcCode +
                    ";\n}"
                );

                firstTime = false;
              } else {
                mutated.insertBefore(
                  'else if(BuildConfig.MUID != null && BuildConfig.MUID.equals("' +
                    mutantId +
                    '")){\n' +
                    mutated.srcCode +
                    ";\n}"
                );
              }
              mutationPoints--;
            } else {
              mutated.insertBefore(
                'else if (BuildConfig.MUID != null && BuildConfig.MUID.equals("' +
                  mutantId +
                  '")){\n' +
                  mutated.srcCode +
                  ";\n}else{\n\t"
              );
              mutated.insertAfter("}");
            }
          } else {
            mutated.insertBefore(
              'if (BuildConfig.MUID != null && BuildConfig.MUID.equals("' +
                mutantId +
                '")){\n' +
                mutated.srcCode +
                ";\n}else{\n\t"
            );
            mutated.insertAfter("}");
          }
        }
      }

      mutator.restore();
    }
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
