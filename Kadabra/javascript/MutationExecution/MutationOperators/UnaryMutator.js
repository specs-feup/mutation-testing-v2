laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class UnaryMutator extends Mutator {
    constructor(original, result) {
        super("UnaryMutator")
        this.original = original;
        this.result = result;
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.putSemicolon = "";

        this.leftHand = ["_++", "_--"];
        this.rightHand = ["++_", "--_", "-_", "+_"];
    }

    isAndroidSpecific() {
        return false;
    }

    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp(joinpoint) {

        if (joinpoint != undefined && joinpoint.parent != undefined && joinpoint.instanceOf("unaryExpression") && !(joinpoint.parent.type == "for") &&
            joinpoint.operator == this.original) {

            if (this.rightHand.contains(this.original) && this.rightHand.contains(this.result)) {
                if (joinpoint.parent.type == undefined) { this.putSemicolon = ";"; } else { this.putSemicolon = ""; }
                this.mutationPoints.push(joinpoint);
            }
            else if (this.leftHand.contains(this.original) && this.leftHand.contains(this.result)) {
                if (joinpoint.parent.type == undefined) { this.putSemicolon = ";"; } else { this.putSemicolon = ""; }
                this.mutationPoints.push(joinpoint);
            } else {
                println("First Operator cannot be replaced with the Second one");


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
        this.previousValue = this.mutationPoint.toString().replace("Unary Expression:", "");
        pri
        let replaceFor = this.mutationPoint.toString().replace("Unary Expression:", "").replace(this.original.replace("_", ""), this.result.replace("_", ""));

        this.mutationPoint = this.mutationPoint.replaceWith(replaceFor);

        this.currentIndex++;


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");

    }

    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.replaceWith(this.previousValue + this.putSemicolon);
        this.previousValue = undefined;
    }

    toString() {
        return `Unary Mutator from ${this.original} to ${this.result}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [this.original, this.result],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }

}
