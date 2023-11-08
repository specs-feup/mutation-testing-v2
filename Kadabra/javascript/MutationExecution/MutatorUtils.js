class MutatorUtils {

    /**
     * 
     * @param {*} stmt 
     * @returns true if given statement needs a semi-colon, false otherwise
     */
    static needsSemiColon(stmt) {

        if (stmt.instanceOf("if") || stmt.instanceOf("loop") || stmt.instanceOf("try") || stmt.instanceOf("switch")) {
            //println("NOT ADDING ; ->  " + stmt.joinPointType);
            return false;
        }

        //println("ADDING ; ->  " + stmt.joinPointType);
        return true;
    }

    /**
     * 
     * @param {*} $stmt 
     * @returns {Boolean} true if the given node returns in some way (return instruction, throw instruction, etc), false if execution continues from that point on
     */
    static isReturningStmt($stmt) {

        if ($stmt.instanceOf("return") || $stmt.instanceOf("throw")) {
            return true;
        }

        // If 'try', body and catches must all return true for it to be a returning stmt
        if ($stmt.instanceOf("try")) {
            let result = MutatorUtils.isReturningStmt($stmt.body);

            for (var currentCatch of $stmt.catches) {
                //println("CATCH AST:\n" + currentCatch.ast);
                //println("CATCH BODY AST:\n" + currentCatch.body.ast);
                result = result & MutatorUtils.isReturningStmt(currentCatch.body);
            }

            return result;
        }

        // If 'switch', true if all cases return
        if ($stmt.instanceOf('switch')) {
            let result = true;
            for (const switchCase of $stmt.cases) {
                const stmts = switchCase.stmts;

                // If empty, assume fall-through to next case,
                // leaving result unchanged
                if (stmts.length === 0) {
                    continue;
                }

                // Apply to last statement of case
                result = result & MutatorUtils.isReturningStmt(stmts[stmts.length - 1]);
            }

            return result;
        }

        // Check last statement of body
        if ($stmt.instanceOf('body')) {
            //println("BODY")
            println($stmt.ast);

            const lastStmt = $stmt.lastStmt;

            // No last statement, no return
            if (lastStmt === undefined) {
                return false;
            }

            return MutatorUtils.isReturningStmt(lastStmt);
        }

        //println("IS RETURN FALSE FOR: " + $stmt.joinPointType);


        return false;
    }

}