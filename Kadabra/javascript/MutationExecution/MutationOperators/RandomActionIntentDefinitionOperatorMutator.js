laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class RandomActionIntentDefinitionOperatorMutator extends Mutator {
    constructor() {
        super("RandomActionIntentDefinitionOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.package = undefined;
        this.targetValues = ["new android.content.Intent(android.content.Intent.ACTION_VIEW)", "new android.content.Intent(android.content.Intent.ACTION_SEND)"];

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

        if (joinpoint != undefined && joinpoint.parent != undefined &&
            joinpoint.type === "Intent" && joinpoint.instanceOf('expression') && !joinpoint.instanceOf('var') && joinpoint.parent != undefined && !joinpoint.parent.instanceOf('var') && joinpoint.parent.type === undefined
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

        let randomValue = Math.floor(Math.random() * this.targetValues.length);

        this.mutationPoint = this.mutationPoints[this.currentIndex];


        while (this.targetValues[randomValue] == this.mutationPoint) {
            randomValue = Math.floor(Math.random() * this.targetValues.length);
        }
        this.previousValue = this.mutationPoint;


        this.mutationPoint = this.mutationPoint.insertReplace(this.targetValues[randomValue]);
        this.currentIndex++;

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
        return `Random Action Intent Definition Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
}
