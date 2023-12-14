laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class BuggyGUIListenerOperatorMutator extends Mutator {
    constructor() {
        super("BuggyGUIListenerOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }

  isAndroidSpecific(){
  return true;
}
    addJp(joinpoint) {
        if (
            joinpoint.type === "OnClickListener" && joinpoint.instanceOf('expression') && !joinpoint.instanceOf('var') 
            // This is to ensure it works on MutantSchemata
            && joinpoint.ancestor("statement") !== undefined 
        ) {
            this.mutationPoints.push(joinpoint);
        }
        if (this.mutationPoints.length > 0) {
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

        this.previousValue = this.mutationPoint;
        this.mutationPoint = this.mutationPoint.insertReplace("null");


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


    }
    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Buggy GUI Listener Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
