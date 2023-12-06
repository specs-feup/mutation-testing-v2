/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {

    if(file.name === "DownloadsSection.java" || file.name === "SubscriptionsSection.java") {
        //println(Query.search("method", "onCreateView").first().ast)
        patchViewBinding(file);
    }
}

function patchViewBinding(file) {
    for(const fieldAccess of Query.searchFrom(file, "method", "onCreateView").search("fieldAccess", {code: code => code.endsWith(".viewBinding")})) {
        //println("FOUND!\n" + fieldAccess.code)
        const stmt = fieldAccess.ancestor("statement");
        const stmtCode = stmt.code;

        if(stmtCode.startsWith("de.danoeh.antennapod.ui.home.HomeSection.viewBinding")) {
            stmt.replaceWith(stmtCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, stmtCode.length));                
        } else {
            const fieldAccessCode = fieldAccess.code;
            fieldAccess.replaceWith(fieldAccessCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, fieldAccessCode.length));
        }
        

        //println("Stmt: " + stmt.code);
    }


}


globalThis.patchFile = patchFile;
globalThis.AntennaPod = {"patchFile": patchFile}