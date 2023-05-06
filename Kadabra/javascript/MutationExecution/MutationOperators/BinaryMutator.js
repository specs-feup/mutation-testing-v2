laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class BinaryMutator extends Mutator {

  constructor(original, result) {
    super("BinaryMutator");

    this.original = original;
    this.result = result;
    this.mutationPoints = [];
    this.currentIndex = 0;
    this.mutationPoint = undefined;
    this.previousValue = undefined;

    this.arithmeticOperators = ["+", "-", "*", "/", "%"];
    this.bitwiseOperators = ["&", "|", "<<", ">>"];
    this.comparisonOperators = ["==", "!=", ">", "<", ">=", "<="];
    this.logicalOperators = ["&&", "||"];
    this.assignmentOperators = ["=", "+=", "-=", "*=", "/=", "%="];
  }




  /*** IMPLEMENTATION OF INSTANCE METHODS ***/
  addJp(joinpoint) {

    if (
      joinpoint.instanceOf("binaryExpression") &&
      joinpoint.operator === this.original
    ) {


      if (this.arithmeticOperators.contains(this.original) && this.arithmeticOperators.contains(this.result) && !(joinpoint.type === "String")) {

        this.mutationPoints.push(joinpoint);
      }
      else if (this.bitwiseOperators.contains(this.original) && this.bitwiseOperators.contains(this.result)) {

        this.mutationPoints.push(joinpoint);
      }
      else if (this.comparisonOperators.contains(this.original) && this.comparisonOperators.contains(this.result)) {
        this.mutationPoints.push(joinpoint);

      } else if (this.logicalOperators.contains(this.original) && this.logicalOperators.contains(this.result)) {
        this.mutationPoints.push(joinpoint);
      }
      else if (this.assignmentOperators.contains(this.original) && this.assignmentOperators.contains(this.result)) {
        this.mutationPoints.push(joinpoint);
      }
      else {
        println("First Operator cannot be replaced with the Second one");
        //return false;
      }
      return true;
    }
    return false;
  }


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

    this.mutationPoint = this.mutationPoints[this.currentIndex];
    this.currentIndex++;

    //debug(`{this.getName()}: from {this.mutationPoint} to {this.expr}`);

    this.previousValue = this.mutationPoint.operator;


    this.mutationPoint.operator = this.result;



    println("/*--------------------------------------*/");
    println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
      + " to " + this.mutationPoint);
    println("/*--------------------------------------*/");

  }

  _restorePrivate() {

    this.mutationPoint.operator = this.previousValue;

    // println("Restore_mutationPoint " + this.mutationPoint)
    this.previousValue = undefined;
    this.mutationPoint = undefined;
  }

  toString() {
    return `BinaryMutator from ${this.original} to ${this.result}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
  }
  toJson() {
    return {
      mutationOperatorArgumentsList: {
        mutationOperatorFirstArgument: this.original,
        mutationOperatorSecondArgument: this.result,
      },
      operator: this.name,
    };
  }
}
