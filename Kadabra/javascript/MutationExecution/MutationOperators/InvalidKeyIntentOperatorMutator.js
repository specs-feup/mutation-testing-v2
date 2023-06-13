laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class InvalidKeyIntentOperatorMutator extends Mutator {
    constructor() {
        super("InvalidKeyIntentOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific() {
        return true;
    }

    /*&&
            joinpoint.typeReference === "Intent" &&
            joinpoint.name === "<init>"
            && joinpoint.type === "Executable"
    /*** IMPLEMENTATION OF INSTANCE METHODS ***/

    addJp(joinpoint) {


        if (joinpoint.type === "Intent" && joinpoint.instanceOf('expression') && !joinpoint.instanceOf('var') && !joinpoint.parent.instanceOf("if") && joinpoint.parent.type === undefined && joinpoint!=undefined
        ) {
            if (joinpoint.children[0].name === "<init>" && joinpoint.children[0].type === "Executable") {
                if (joinpoint.children[1] != undefined) {
                    this.mutationPoints.push(joinpoint.children[1]);
                }
            }


        } if (this.mutationPoints.length > 0) {
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

        this.mutationPoint = this.mutationPoint.insertReplace("\"" + null + "\"");


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


        println(" this.mutationPoint" + this.mutationPoint);
    }



    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Invalid Key Intent Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
