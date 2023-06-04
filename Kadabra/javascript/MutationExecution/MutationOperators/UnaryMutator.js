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

        this.leftHand = ["_++", "_--"];
        this.rightHand = ["++_", "--_", "-_", "+_"];
    }

    isAndroidSpecific() {
        return false;
    }

    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp(joinpoint) {

        if (
            joinpoint.instanceOf("unaryExpression") && !(joinpoint.parent.type == "for") &&
            joinpoint.operator == this.original) {

            if (this.rightHand.contains(this.original) && this.rightHand.contains(this.result)) {
                this.mutationPoints.push(joinpoint);
            }
            else if (this.leftHand.contains(this.original) && this.leftHand.contains(this.result)) {

                this.mutationPoints.push(joinpoint);
            }

            else {
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

        let replaceFor = this.mutationPoint.toString().replace("Unary Expression:", "").replace(this.original.replace("_", ""), this.result.replace("_", ""));

        this.mutationPoint = this.mutationPoint.replaceWith(replaceFor);

        this.currentIndex++;


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");

    }

    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.replaceWith(this.previousValue);
        this.previousValue = undefined;
    }

    toString() {
        return `Unary Mutator from ${this.original} to ${this.result}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }

}