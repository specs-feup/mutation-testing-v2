import Io from "@specs-feup/lara/api/lara/Io.js";
import Strings from "@specs-feup/lara/api/lara/Strings.js";
import Query from "@specs-feup/lara/api/weaver/Query.js";
import Script from "@specs-feup/lara/api/weaver/Script.js";
import { mutatorList } from "./MutatorList.js";
import { changeVarDeclarations } from "./Decomposition.js";
import { setDebug, debug } from "@specs-feup/lara/api/lara/core/LaraCore.js";
import { println } from "@specs-feup/lara/api/core/output.js";

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
  for (var $jp of Query.root().descendants) {
    var $call = $jp.getAncestor("call");

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
            : mutator.getMutationPoint().getAncestor("statement");
        }

        //print(mutator.toJson());

        if (needElseIf) {
          if (mutationPoints > 1) {
            if (firstTime) {
              mutated.insertBefore(
                'if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("' +
                  mutantId +
                  '")){\n' +
                  mutated.srcCode +
                  ";\n}"
              );

              firstTime = false;
            } else {
              mutated.insertBefore(
                'else if(System.getProperty("MUID") != null && System.getProperty("MUID").equals("' +
                  mutantId +
                  '")){\n' +
                  mutated.srcCode +
                  ";\n}"
              );
            }
            mutationPoints--;
          } else {
            mutated.insertBefore(
              'else if (System.getProperty("MUID") != null && System.getProperty("MUID").equals("' +
                mutantId +
                '")){\n' +
                mutated.srcCode +
                ";\n}else{\n\t"
            );
            mutated.insertAfter("}");
          }
        } else {
          mutated.insertBefore(
            'if (System.getProperty("MUID") != null && System.getProperty("MUID").equals("' +
              mutantId +
              '")){\n' +
              mutated.srcCode +
              ";\n}else{\n\t"
          );
          mutated.insertAfter("}");
        }
        mutator.restore();
      }
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
