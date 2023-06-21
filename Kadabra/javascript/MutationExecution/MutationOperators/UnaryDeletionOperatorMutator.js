laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class UnaryDeletionOperatorMutator extends Mutator {
    constructor(targetConstant) {
        //Parent constructor
        super("UnaryDeletionOperatorMutator");
        this.targetConstant = [targetConstant, '(' + targetConstant + ')'];
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.previousValue = undefined;
    }

    isAndroidSpecific() {
        return false;
    }

    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp(joinpoint) {

        if (joinpoint.instanceOf('binaryExpression')) {

            var lhs = joinpoint.lhs;
            var rhs = joinpoint.rhs;
            if (((this.targetConstant.contains(lhs.srcCode) && lhs.isFinal)
                || (this.targetConstant.contains(rhs.srcCode) && rhs.isFinal))
                && joinpoint.type !== 'boolean') {

                this.toMutate.push(joinpoint);
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
        this.currentIndex++;

        this.previousValue = this.mutationPoint;

        if (this.targetConstant.contains(this.mutationPoint.lhs.srcCode)) {
            this.mutationPoint = this.mutationPoint.insertReplace(this.mutationPoint.rhs);
        } else if (this.targetConstant.contains(this.mutationPoint.rhs.srcCode)) {
            this.mutationPoint = this.mutationPoint.insertReplace(this.mutationPoint.lhs);
        }

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

        this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }
    toString() {
        return `Unary Deletion Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }
    toJson() {
        return {
            mutationOperatorArgumentsList: [this.targetConstant],
            operator: this.name,
        };
    }
}








