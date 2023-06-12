
laraImport("IntentTargetReplacementOperatorMutator");

laraImport("InvalidMethodCallArgumentOperatorMutator");
laraImport("RemoveConditionalMutator");
laraImport("NullIntentOperatorMutator");
laraImport("BinaryMutator");
laraImport("ReturnValueMutator");
laraImport("NullifyReturnValue");
laraImport("ConditionalExpressionReplacementOperatorMutator");


laraImport("lara.Io");
laraImport("weaver.Query");
laraImport("kadabra.KadabraNodes");



const fileName="/";

const outputPath = "/Users/anaritaveiga/KadabraFiles/outputAqui/app";
const filePath = "/Users/anaritaveiga/mutation-testing/TestProject/src/main/java/app/TestCases.java";
const projectPath = "/Users/anaritaveiga/AntennaPod/app/src";

const fileName1 = filePath.substring(
filePath.lastIndexOf(Io.getSeparator()) + 1);

 

 for (var jp of Query.root().descendants) {


   if(jp.instanceOf("localVariable") && jp.numChildren>=2 && !(jp.parent.type =="for")&& !(jp.parent.instanceOf('try'))){
      // Create write reference to local variable
      let localVar = KadabraNodes.var(jp, true);

      // Get initialization, and remove it from declaration
      const varLhs = jp.init;
      jp.init = undefined;

      // Create assignment
      const varAssign = KadabraNodes.assignment(localVar, varLhs);

      // Add assignment after initialization
      jp.insertAfter(varAssign);
    }
  }



println("ACABEIIIIII");




//println(""+ fileName);
var mutatorList=[
  	//new ConditionalMutator("?:"),
	// new BinaryMutator("==", "<="),
     new BinaryMutator("<", ">"),
	//new UnaryAddOperatorMutator("++"),
	//new AssignmentOperatorMutator("+=","-="),
	//new AssignmentOperatorMutator("-=","/="),
    //  new ConstructorCallMutator(),
   //new NullIntentOperatorMutator(),
   // new InvalidKeyIntentPutExtraOperatorMutator(),
  //  new NullValueIntentPutExtraOperatorMutator(),
    //new RandomActionIntentDefinitionOperatorMutator(),
   //new InvalidKeyIntentPutExtraOperatorMutator(),
    // new InvalidValueIntentPutExtraOperatorMutator(),
  // new IntentPayloadReplacementOperatorMutator(),
//	new InvalidDateOperatorMutator(),
//new InvalidMethodCallArgumentOperatorMutator(),
//new StringArgumentReplacementOperatorMutator(),
//new StringCallReplacementOperatorMutator(),
//new NotSerializableOperatorMutator(),
//new ActivityNotDefinedOperatorMutator(),
//new ConstantMutator("1"),
//new ConditionalAddNotOperatorMutator(),
//new IntentTargetReplacementOperatorMutator(),
//new XMLChangeIntentFilterReceiverOperatorMutator(),
//new XMLChangeIntentFilterServiceOperatorMutator(),
//new XMLActivityNotDefinedOperatorMutator(".MainActivity"),
//new XMLInvalidColorOperatorMutator(),
//new XMLButtonWidgetDeletionOperatorMutator(),

//new XMLButtonWidgetInvisibleOperatorMutator(),
//new XMLEditTextWidgetDeletionOperatorMutator(),

//new XMLEditTextWidgetInvisibleOperatorMutator(),
//new XMLTextViewWidgetDeletionOperatorMutator(),
// new XMLViewGroupWidgetInvisibleOperatorMutator(),
// new XMLViewGroupWidgetChangeTypeOperatorMutator(),
// new XMLEditTextWidgetChangeAppearanceOperatorMutator(),
// new XMLButtonWidgetChangeAppearanceOperatorMutator(),
//new UnaryMutator("_++","_--")
//new BinaryOperatorDeletionMutator(),
//new XMLButtonWidgetTextSwitchOperatorMutator(),
//new ConstructorCallOperatorMutator(),
//new NullifyReturnValue(),
//new ReturnValueMutator(),
//new RemoveConditionalMutator(),
//new ConditionalExpressionReplacementOperatorMutator(),
];



  let mutationPoints = 0;

  var mutantList = [];

  const auxFunction = "public static String getMUID(){ \
  String propertyValue = null; \
  try { \
  Process process = Runtime.getRuntime().exec(\"getprop MUID\"); \  InputStream inputStream = process.getInputStream(); \
  BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); \
  propertyValue = reader.readLine();\
  reader.close();\
  inputStream.close();\
  } catch (IOException e) {\
  Log.e(\"ERROR\", String.valueOf(e));\
  }\
  return propertyValue;\
  }";

  const imports = "import java.io.BufferedReader;\n import java.io.IOException;\n import java.io.InputStream;\n import java.io.InputStreamReader;\n import android.util.Log; ";
println("aaaa");
  for (var $jp of Query.search("class")) {
    $jp.insertBefore(imports);
    $jp.insertMethod(auxFunction);
    break;
  }

  //Query.search(FIle)
  for (var $jp of Query.root().descendants) {
    var $call = $jp.ancestor("call");

    // Ignore nodes that are children of $call with the name <init>
    if ($call !== undefined && $call.name === "<init>") continue;

    // Ignore nodes inside variable declarations
    //if ($jp.ancestor("localVariable") !== undefined) {
    //  continue;
    //}

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
        // Mutate
        mutator.mutate();

        var mutated = mutator.getMutationPoint().isStatement
          ? mutator.getMutationPoint()
          : mutator.getMutationPoint().ancestor("statement");

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

        //print(mutator.toJson());

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
        mutator.restore();
      }
      saveFile(mutator);
    }
  }


function saveFile(mutator){
let relativePath = Io.getRelativePath(filePath, projectPath);

  let newFolder =fileName+
    mutator.getName() + ".java"
    ;




  Io.writeFile(
    outputPath+ Io.getSeparator() +newFolder ,
    Query.root().code
  );
}