laraImport("MutationOperators.*");

const classes = {
  AssignmentOperatorMutator,
  BinaryMutator,
};

const mutatorList = getMutators();

function getMutators() {
  let operatorNameList = laraArgs.operatorNameList;
  let operatorArgumentList = laraArgs.operatorArgumentList;

  Mutators = [];
  for (i in operatorNameList) {
    var classname = operatorNameList[i];
    var args = operatorArgumentList[i];
    var operator = new classes[classname](...args);
    Mutators.push(operator);
  }

  return Mutators;
}
