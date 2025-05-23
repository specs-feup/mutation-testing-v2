import AssignmentOperatorMutator from "./MutationOperators/AssignmentOperatorMutator.js";
import BinaryMutator from "./MutationOperators/BinaryMutator.js";
import BinaryOperatorDeletionMutator from "./MutationOperators/BinaryOperatorDeletionMutator.js";
import BuggyGUIListenerOperatorMutator from "./MutationOperators/BuggyGUIListenerOperatorMutator.js";
import ConditionalExpressionReplacementOperatorMutator from "./MutationOperators/ConditionalExpressionReplacementOperatorMutator.js";
import ConstantOperatorMutator from "./MutationOperators/ConstantOperatorMutator.js";
import ConstructorCallOperatorMutator from "./MutationOperators/ConstructorCallOperatorMutator.js";
import FailOnNullOperatorMutator from "./MutationOperators/FailOnNullOperatorMutator.js";
import FindViewByIdDeletionMutator from "./MutationOperators/FindViewByIdDeletionMutator.js";
import FindViewByIdReturnsNullOperatorMutator from "./MutationOperators/FindViewByIdReturnsNullOperatorMutator.js";
import ForLoopReplacementOperatorMutator from "./MutationOperators/ForLoopReplacementOperatorMutator.js";
import IntentPayloadReplacementOperatorMutator from "./MutationOperators/IntentPayloadReplacementOperatorMutator.js";
import IntentTargetReplacementOperatorMutator from "./MutationOperators/IntentTargetReplacementOperatorMutator.js";
import InvalidDateOperatorMutator from "./MutationOperators/InvalidDateOperatorMutator.js";
import InvalidIDFindViewOperatorMutator from "./MutationOperators/InvalidIDFindViewOperatorMutator.js";
import InvalidKeyIntentOperatorMutator from "./MutationOperators/InvalidKeyIntentOperatorMutator.js";
import InvalidKeyIntentPutExtraOperatorMutator from "./MutationOperators/InvalidKeyIntentPutExtraOperatorMutator.js";
import InvalidMethodCallArgumentOperatorMutator from "./MutationOperators/InvalidMethodCallArgumentOperatorMutator.js";
import InvalidViewFocusOperatorMutator from "./MutationOperators/InvalidViewFocusOperatorMutator.js";
import LengthyGUICreationOperatorMutator from "./MutationOperators/LengthyGUICreationOperatorMutator.js";
import LengthyGUIListenerOperatorMutator from "./MutationOperators/LengthyGUIListenerOperatorMutator.js";
import NonVoidCallMutator from "./MutationOperators/NonVoidCallMutator.js";
import NotSerializableOperatorMutator from "./MutationOperators/NotSerializableOperatorMutator.js";
import NullBluetoothAdapterOperatorMutator from "./MutationOperators/NullBluetoothAdapterOperatorMutator.js";
import NullGPSLocationOperatorMutator from "./MutationOperators/NullGPSLocationOperatorMutator.js";
import NullifyInputVariable from "./MutationOperators/NullifyInputVariable.js";
import NullifyReturnValue from "./MutationOperators/NullifyReturnValue.js";
import NullIntentOperatorMutator from "./MutationOperators/NullIntentOperatorMutator.js";
import NullMethodCallArgumentOperatorMutator from "./MutationOperators/NullMethodCallArgumentOperatorMutator.js";
import NullValueIntentOperatorMutator from "./MutationOperators/NullValueIntentOperatorMutator.js";
import NullValueIntentPutExtraOperatorMutator from "./MutationOperators/NullValueIntentPutExtraOperatorMutator.js";
import RandomActionIntentDefinitionOperatorMutator from "./MutationOperators/RandomActionIntentDefinitionOperatorMutator.js";
import RemoveConditionalMutator from "./MutationOperators/RemoveConditionalMutator.js";
import ReturnValueMutator from "./MutationOperators/ReturnValueMutator.js";
import StringArgumentReplacementOperatorMutator from "./MutationOperators/StringArgumentReplacementOperatorMutator.js";
import StringCallReplacementOperatorMutator from "./MutationOperators/StringCallReplacementOperatorMutator.js";
import UnaryAddOperatorMutator from "./MutationOperators/UnaryAddOperatorMutator.js";
import UnaryDeletionOperatorMutator from "./MutationOperators/UnaryDeletionOperatorMutator.js";
import UnaryMutator from "./MutationOperators/UnaryMutator.js";
import ViewComponentNotVisibleOperatorMutator from "./MutationOperators/ViewComponentNotVisibleOperatorMutator.js";
import XMLButtonWidgetChangeAppearanceOperatorMutator from "./MutationOperators/XMLButtonWidgetChangeAppearanceOperatorMutator.js";
import XMLButtonWidgetDeletionOperatorMutator from "./MutationOperators/XMLButtonWidgetDeletionOperatorMutator.js";
import XMLButtonWidgetInvisibleOperatorMutator from "./MutationOperators/XMLButtonWidgetInvisibleOperatorMutator.js";
import XMLEditTextWidgetChangeAppearanceOperatorMutator from "./MutationOperators/XMLEditTextWidgetChangeAppearanceOperatorMutator.js";
import XMLEditTextWidgetDeletionOperatorMutator from "./MutationOperators/XMLEditTextWidgetDeletionOperatorMutator.js";
import XMLEditTextWidgetInvisibleOperatorMutator from "./MutationOperators/XMLEditTextWidgetInvisibleOperatorMutator.js";
import XMLInvalidColorOperatorMutator from "./MutationOperators/XMLInvalidColorOperatorMutator.js";
import XMLTextViewWidgetDeletionOperatorMutator from "./MutationOperators/XMLTextViewWidgetDeletionOperatorMutator.js";
import XMLViewGroupWidgetChangeTypeOperatorMutator from "./MutationOperators/XMLViewGroupWidgetChangeTypeOperatorMutator.js";
import XMLViewGroupWidgetInvisibleOperatorMutator from "./MutationOperators/XMLViewGroupWidgetInvisibleOperatorMutator.js";

import Weaver from "@specs-feup/lara/api/weaver/Weaver.js";

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
export const MutatorList = {};

const laraArgs = Weaver.laraArgs;

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
export const mutatorList = MutatorList.getMutators();
