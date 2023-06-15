laraImport("MutationOperators.*");

const classes = {
  AssignmentOperatorMutator,
  BinaryMutator,
  BinaryOperatorDeletionMutator,
  ConstantOperatorMutator,
  FindViewByIdDeletionMutator,
  IntentPayloadReplacementOperatorMutator,
  InvalidDateOperatorMutator,
  InvalidKeyIntentOperatorMutator,
  InvalidKeyIntentPutExtraOperatorMutator,
  InvalidMethodCallArgumentOperatorMutator,
  InvalidValueIntentPutExtraOperatorMutator,
  NonVoidCallMutator,
  ConstructorCallOperatorMutator,
  UnaryMutator,
  NotSerializableOperatorMutator,
  NullIntentOperatorMutator,
  StringCallReplacementOperatorMutator,
  FailOnNullOperatorMutator,
  ConditionalExpressionReplacementOperatorMutator,
  StringArgumentReplacementOperatorMutator,
  InvalidDateOperatorMutator,
  NullValueIntentOperatorMutator,
  RandomActionIntentDefinitionOperatorMutator,
  RemoveConditionalMutator,
  RemoveNullCheck,
  ForLoopReplacementOperatorMutator,
  NullMethodCallArgumentOperatorMutator,
  NullifyReturnValue,
  StringArgumentReplacementOperatorMutator,
  UnaryAddOperatorMutator,
  UnaryDeletionOperatorMutator,
  StringCallReplacementOperatorMutator,
  ReturnValueMutator,
  BuggyGUIListenerOperatorMutator,
  IntentTargetReplacementOperatorMutator,
  FindViewByIdReturnsNullOperatorMutator,
  InvalidIDFindViewOperatorMutator,
  LengthyGUICreationOperatorMutator,
  LengthyGUIListenerOperatorMutator,
  ViewComponentNotVisibleOperatorMutator,
  NullGPSLocationOperatorMutator,
  NullBluetoothAdapterOperatorMutator,
  XMLInvalidColorOperatorMutator,
  XMLButtonWidgetDeletionOperatorMutator,
  XMLEditTextWidgetDeletionOperatorMutator,
  XMLButtonWidgetInvisibleOperatorMutator,
  XMLEditTextWidgetInvisibleOperatorMutator,
  XMLViewGroupWidgetChangeTypeOperatorMutator,
  XMLEditTextWidgetChangeAppearanceOperatorMutator,
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
