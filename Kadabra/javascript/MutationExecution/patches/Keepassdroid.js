laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "GroupBaseActivity.java" ) {
        removeFinal("mGroup")
    } else if(file.name === "EntryEditActivity.java" ) {
        removeFinal("mEntry")
    } else if(file.name === "DateUtil.java" ) {
        removeFinal("epochOffset")
    } else if(file.name === "PwDatabase.java") {
        removeFinal("rootGroup")
    } else if (file.name === "AbstractHashedMap.java") {
        removeFinal("data")
    }
}

function removeFinal(fieldName) {
    Query.search("field", fieldName).first().removeModifier("final");
}

globalThis.patchFile = patchFile;
globalThis.Keepassdroid = {"patchFile": patchFile}