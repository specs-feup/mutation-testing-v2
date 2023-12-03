laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");
laraImport("MutatorUtils");

/***
 * TODO: This operator seems to not be working, is taking certain assumptions with the code and is not making the necessary verifications.
 * 
 * It seems it wants to find code of the kind 'a = ...findViewById(), but:
 * - Is not verifying if there is an assign statement
 * - Is not verifying if the call is the return value of the assign statement
 * - Mutation is also not correct since it is inserting code  after, and not replacing the mutation point (or statement of the point) itself
 */
class InvalidViewFocusOperatorMutator extends Mutator {
    constructor() {
        super("InvalidViewFocusOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific() {
        return true;
    }
    addJp(joinpoint) {

        if (joinpoint.instanceOf('call')
        ) {

            //if (joinpoint.children[0] == 'findViewById - Executable') {
            if (joinpoint.name === 'findViewById') {

                // Parent must be an assignment, and right side must be this join point
                if (joinpoint.parent.instanceOf("assignment") && joinpoint.compareNodes(joinpoint.parent.rhs)) {
                    // TODO: should not store parent, but point itself
                    this.mutationPoints.push(joinpoint.parent);
                    //this.mutationPoints.push(joinpoint);    
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
        //println("Mutation point: " + this.mutationPoint.code)
        const stmt = this.mutationPoint.isStatement ? this.mutationPoint : this.mutationPoint.ancestor("statement");
        //println("Mutation point statement: " + stmt.ast)

        // Mutation point is assignment, use left-hand
        const variable = this.mutationPoint.lhs.code;
        //const variable = this.mutationPoint.toString().split("=")[0].trim();
        //println("Variable: " + variable)
        
        let toReplaceWith = variable + ".requestFocus();"
        this.currentIndex++;

        //println("Type: " + stmt.joinPointType);
        //println("Needs semicolon?: " + MutatorUtils.needsSemiColon(stmt))

        //const semiColon = MutatorUtils.needsSemiColon(stmt) ? ";" : "";
        //const code = stmt.code + semiColon + "\n" + toReplaceWith;
		const code = MutatorUtils.getStatementCode(stmt) + "\n" + toReplaceWith;
        //println("Code: " + code)

        this.previousValue = this.mutationPoint;
        //this.mutationPoint = this.mutationPoint.insertAfter(toReplaceWith);
        this.mutationPoint = this.mutationPoint.replaceWith(code);


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


    }
    _restorePrivate() {

        //this.mutationPoint = this.mutationPoint.replaceWith("");
        this.mutationPoint.replaceWith(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Invalid  View Focus Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: []
            ,
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
