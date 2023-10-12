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
  NullValueIntentPutExtraOperatorMutator,
  NonVoidCallMutator,
  ConstructorCallOperatorMutator,
  UnaryMutator,
  UnaryAddOperatorMutator,
  NotSerializableOperatorMutator,
  NullIntentOperatorMutator,
  StringCallReplacementOperatorMutator,
  FailOnNullOperatorMutator,
  ConditionalExpressionReplacementOperatorMutator,
  StringArgumentReplacementOperatorMutator,
  NullValueIntentOperatorMutator,
  RandomActionIntentDefinitionOperatorMutator,
  RemoveConditionalMutator,
  NullifyInputVariable,
  ForLoopReplacementOperatorMutator,
  NullMethodCallArgumentOperatorMutator,
  NullifyReturnValue,
  UnaryDeletionOperatorMutator,
  ReturnValueMutator,
  BuggyGUIListenerOperatorMutator,
  InvalidViewFocusOperatorMutator,
  IntentTargetReplacementOperatorMutator,
  FindViewByIdReturnsNullOperatorMutator,
  InvalidIDFindViewOperatorMutator,
  LengthyGUICreationOperatorMutator,
  LengthyGUIListenerOperatorMutator,
  ViewComponentNotVisibleOperatorMutator,
  NullGPSLocationOperatorMutator,
  NullBluetoothAdapterOperatorMutator,
  XMLInvalidColorOperatorMutator,
  XMLEditTextWidgetChangeAppearanceOperatorMutator,
  XMLButtonWidgetChangeAppearanceOperatorMutator,
  XMLViewGroupWidgetChangeTypeOperatorMutator,
  XMLButtonWidgetInvisibleOperatorMutator,
  XMLEditTextWidgetInvisibleOperatorMutator,
  XMLViewGroupWidgetInvisibleOperatorMutator,
  XMLEditTextWidgetDeletionOperatorMutator,
  XMLButtonWidgetDeletionOperatorMutator,
  XMLTextViewWidgetDeletionOperatorMutator,
};

// To avoid a warning, and follow the convention that a JS file of a given name exposes a variable with the same name
const MutatorList = {};


MutatorList.getMutators = function() {
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

// backwards compatibility
const mutatorList = MutatorList.getMutators();
