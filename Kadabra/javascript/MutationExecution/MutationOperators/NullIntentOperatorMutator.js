laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");
class NullIntentOperatorMutator extends Mutator {
    constructor() {
        super("NullIntentOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific() {
        return true;
    }
    addJp(joinpoint) {

        if (joinpoint != undefined) {
            // If part of a member call, do not apply
            if(joinpoint.ancestor("call") !== undefined) {
                return false;
            }

            if (joinpoint.type != undefined) {
                if (joinpoint.type == "Intent" && joinpoint.children[0] != undefined && joinpoint.children[0].name != undefined && joinpoint.children[0].name === "<init>" && joinpoint.children[0].type != undefined && joinpoint.children[0].type === "Executable" && joinpoint.parent != undefined && !joinpoint.parent.instanceOf('var') && joinpoint.type != "Package" && joinpoint.parent != undefined
                ) {

                    this.mutationPoints.push(joinpoint);

                }
            }
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

        this.previousValue = this.mutationPoint;
        this.mutationPoint = this.mutationPoint.insertReplace("null");


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");

        this.currentIndex++;

    }
    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Null Intent Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, 
        current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }
    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
