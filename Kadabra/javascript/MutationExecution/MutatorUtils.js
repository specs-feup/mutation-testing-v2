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
}