laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class UnaryDeletionOperatorMutator extends Mutator {
    constructor(operator) {
        //Parent constructor
        super("UnaryDeletionOperatorMutator");

        this.operator = operator;
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.previousValue = undefined;
    }



    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp($joinpoint) {

        if (
            !$joinpoint.instanceOf("unaryExpression") || $joinpoint.typeReference.isBoolean
        ) {
            return false;
        } else {


            this.mutationPoints.push($joinpoint);
            println("Adicionou um ponto de mutação " + $joinpoint + " na linha " + $joinpoint.line);
            return true;
        }
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
        println(this.mutationPoint);
        this.currentIndex++;

        this.previousValue = this.mutationPoint;
        console.log(this.mutationPoint);
        this.mutationPoint = KadabraNodes.snippetExpr(this.mutationPoint.descendants.code)
        
        //this.mutationPoint.insertReplace(this.$conditional.cond.operand.copy());

        this.previousValue.replaceWith(this.mutationPoint);



        this.$node = this.toMutate[this.currentIndex++];

		this.$originalNode = this.$node.copy();

		if (this.targetConstant.contains(this.$node.lhs.srcCode)) {
			this.$node = this.$node.insertReplace(this.$node.rhs);
		} else if (this.targetConstant.contains(this.$node.rhs.srcCode)) {
			this.$node = this.$node.insertReplace(this.$node.lhs);
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
        // Restore operator
        println("Restoring " + this.previousValue + " from " + this.mutationPoint);

        this.mutationPoint.replaceWith(this.previousValue);

        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }
}
