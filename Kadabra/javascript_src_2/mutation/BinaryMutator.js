laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class BinaryMutator extends Mutator {
  constructor($original, $result) {
    super("BinaryMutator");

    this.$original = $original;
    this.$expr = $result;
    this.mutationPoints = [];
    this.currentIndex = 0;
    this.mutationPoint = undefined;
    this.previousValue = undefined;
  }

  /*** IMPLEMENTATION OF INSTANCE METHODS ***/
  addJp($joinpoint) {
    if (
      $joinpoint.instanceOf("binaryExpression") &&
      $joinpoint.operator === this.$original
    ) {
      this.mutationPoints.push($joinpoint);
      debug(
        "Adicionou um ponto de mutação " +
          this.$expr +
          " a " +
          $joinpoint +
          " na linha " +
          $joinpoint.line
      );
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

    debug(`${this.getName()}: from ${this.mutationPoint} to ${this.$expr}`);

    this.previousValue = this.mutationPoint.operator;
    this.mutationPoint.operator = this.$expr;
  }

  _restorePrivate() {
    this.mutationPoint.operator = this.previousValue;
    this.previousValue = undefined;
    this.mutationPoint = undefined;
  }

  toString() {
    return `BinaryMutator from ${this.$original} to ${this.$expr}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
  }

  toJson() {
    return {
      original: this.$original,
      change: this.$expr,
      mutationType: "BinaryMutator",
    };
  }
}
