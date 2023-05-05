laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class UnaryAddOperatorMutator extends Mutator {
    constructor(operator) {
        //Parent constructor
        super("UnaryAddOperatorMutator");

        this.operator = operator;
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.previousValue = undefined;
    }



    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp($joinpoint) {
        println("type" + $joinpoint.joinPointType);
        // based on https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op1.html
        if (!this.operator == "++" || !this.operator == "--" || !this.operator == "-" || !this.operator == "+" || !this.operator == "!") {
            return false;
        }

        if (!$joinpoint.instanceOf("var") && !$joinpoint.instanceOf("literal")
        ) {
            return false;
        }

        if ($joinpoint.isBoolean && this.operator === "!") {
            this.mutationPoints.push($joinpoint);
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

        const copy = KadabraNodes.snippetExpr(this.mutationPoint.code)

        this.mutationPoint = KadabraNodes.unaryExpression(this.operator, copy);

        this.previousValue.replaceWith(this.mutationPoint);


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

    toJson() {
        return {
            mutationOperatorArgumentsList: {
                mutationOperatorFirstArgument: this.operator,
            },
            operator: this.name,
        };
    }
}
