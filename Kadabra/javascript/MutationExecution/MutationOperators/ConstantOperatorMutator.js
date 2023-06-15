laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class ConstantOperatorMutator extends Mutator {
  constructor(expr) {
    super("ConstantOperatorMutator");

    this.expr = expr;
    this.newValue = undefined;
    this.mutationPoints = [];
    this.currentIndex = 0;
    this.previousValue = undefined;
  }

  isAndroidSpecific() {
    return false;
  }

  addJp(joinpoint) {
    if (joinpoint.instanceOf("field") ||
      joinpoint.instanceOf("localVariable")
    ) {

      if (joinpoint.init === undefined ||
        !ConstantOperatorMutator._isCompatible(joinpoint.type, this.expr.type)
      ) {

        return false;
      }

      this.mutationPoints.push(joinpoint);

      return true;
    }

    if (joinpoint.instanceOf("assignment")) {
      if (
        !ConstantOperatorMutator._isCompatible(joinpoint.type, this.expr.type)
      ) {
        return false;
      }

      this.mutationPoints.push(joinpoint);

      return true;
    }

    return false;
  }

  static _isCompatible(type1, type2) {

    return true;
  }

  /*** IMPLEMENTATION OF INSTANCE METHODS ***/
  hasMutations() {
    return this.currentIndex < this.mutationPoints.length;
  }

  getMutationPoint() {
    if (this.isMutated) {
      return this.newValue;
    } else {
      if (this.currentIndex < this.mutationPoints.length) {
        return this.mutationPoints[this.currentIndex];
      } else {
        return undefined;
      }
    }
  }

  _mutatePrivate() {
    var mutationPoint = this.mutationPoints[this.currentIndex];

    if (
      mutationPoint.instanceOf("field") ||
      mutationPoint.instanceOf("localVariable")
    ) {
      this.previousValue = mutationPoint.init;
    } else if (mutationPoint.instanceOf("assignment")) {
      this.previousValue = mutationPoint.rhs;
    }

    this.currentIndex++;

    if (isFunction(this.expr)) {
      var tem = this.expr(this.previousValue);
      this.newValue = this.previousValue.insertReplace(tem);
    } else {
      this.newValue = this.previousValue.insertReplace(this.expr);
    }

    println("/*--------------------------------------*/");
    println(
      "Mutating operator n." +
      this.currentIndex +
      ": " +
      this.previousValue +
      " to " +
      this.newValue
    );
    println("/*--------------------------------------*/");
  }

  _restorePrivate() {
    // Restore operator
    this.newValue.insertReplace(this.previousValue);
    this.previousValue = undefined;
    this.newValue = undefined;
  }

  toString() {
    return `Constant Operator Mutator from ${this.previousValue} to ${this.newValue}, current mutation points ${this.mutationPoints}, current mutation point ${this.newValue} and previous value ${this.previousValue}`;
  }
  toJson() {
    return {
      mutationOperatorArgumentsList: {
        mutationOperatorFirstArgument: this.expr,
      },
      operator: this.name,
      isAndroidSpecific: this.isAndroidSpecific(),
    };
  }

}
