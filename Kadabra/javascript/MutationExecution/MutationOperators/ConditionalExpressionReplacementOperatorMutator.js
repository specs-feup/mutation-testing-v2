laraImport("lara.mutation.Mutator");

/**
 *  @param {$joinPoint} $joinPoint - A join point to use as starting point to search for conditionals to insert '!'.
 */
class ConditionalExpressionReplacementOperatorMutator extends Mutator {
    //Parent constructor
    constructor() {

        super("ConditionalExpressionReplacementOperatorMutator");

        this.valuesToAssignExpression2OfConditionalExpression = [];
        this.previousValue = undefined;
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }

    isAndroidSpecific(){
      return false;
    }
    addJp(joinpoint) {


        if (joinpoint.instanceOf('ternary')) {
            for (let i = 0; i < joinpoint.numChildren; i++) {

                if (joinpoint.children[i].instanceOf('var')) {
                    println(joinpoint);

                    this.valuesToAssignExpression2OfConditionalExpression.push(joinpoint.children[i]);

                }
                println(this.valuesToAssignExpression2OfConditionalExpression[i]);
            }
            if (joinpoint.numChildren > 0) {
                this.mutationPoints.push(joinpoint.children[(joinpoint.numChildren - 1)]);
            }

            if (this.mutationPoints.length > 0) {
                this.mutationPoint = this.mutationPoints[this.currentIndex];
            }
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


        let randomIndex = Math.floor(Math.random() * this.valuesToAssignExpression2OfConditionalExpression.length);

        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.previousValue = this.mutationPoint
        println(this.previousValue);
        while (this.previousValue == this.valuesToAssignExpression2OfConditionalExpression[randomIndex].toString()) {
            randomIndex = Math.floor(Math.random() * this.valuesToAssignExpression2OfConditionalExpression.length);

        }
        this.mutationPoint = this.mutationPoint.insertReplace(this.valuesToAssignExpression2OfConditionalExpression[randomIndex].toString());

        this.currentIndex++;
        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


        println(" this.mutationPoint" + this.previousValue);

    }



    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;

    }





    toString() {
        return `Conditional Expression Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
}
