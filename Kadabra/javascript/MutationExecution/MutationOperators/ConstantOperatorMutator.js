laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class ConstantOperatorMutator extends Mutator {
  constructor(expr) {
    super("ConstantOperatorMutator");

    this.expr = expr;
    this.mutationPoint = undefined;
    this.mutationPoints = [];
    this.currentIndex = 0;
    this.previousValue = undefined;
  }

  isAndroidSpecific() {
    return false;
  }

  addJp(joinpoint) {
    if (joinpoint.instanceOf("field") ||
      joinpoint.instanceOf("localVariable") || joinpoint.instanceOf("assignment")
    ) {

      if (joinpoint != undefined && this.expr != undefined && joinpoint.init === undefined ||

        ConstantOperatorMutator._isCompatible(joinpoint.type, this.expr.type)
      ) {
        if (joinpoint != undefined && joinpoint.init != undefined || joinpoint.rhs != undefined) {
          this.mutationPoints.push(joinpoint);
        }

      }
      if (this.mutationPoints.length > 0) { return true; }
      return false;


    }
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
      return this.mutationPoint;
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
    } else if (mutationPoint.instanceOf("assignment") && mutationPoint.rhs != undefined) {

      this.previousValue = mutationPoint.rhs;
    }

    if (this.previousValue != undefined) {
      if (isFunction(this.expr)) {
        var tem = this.expr(this.previousValue);
        this.mutationPoint = this.previousValue.insertReplace(tem);
      } else {
        this.mutationPoint = this.previousValue.insertReplace(this.expr);
      }
    }
    this.currentIndex++;

    println("/*--------------------------------------*/");
    println(
      "Mutating operator n." +
      this.currentIndex +
      ": " +
      this.previousValue +
      " to " +
      this.mutationPoint
    );
    println("/*--------------------------------------*/");
  }

  _restorePrivate() {
     this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
    this.previousValue = undefined;
    this.mutationPoint = undefined;
  }

  toString() {
    return `Constant Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
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
