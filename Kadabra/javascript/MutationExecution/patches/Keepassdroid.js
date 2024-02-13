laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "AbstractReferenceMap.java" || 
        file.name === "PwDatabaseV4.java" ||
        file.name === "DateUtil.java" ||
        file.name ==="EntryEditActivityV3.java") {
        // cahnnels must not be final, otherwise fully qualified names will not work
        Query.search("field", "channels").first().removeModifier("final");
    } 
}

globalThis.patchFile = patchFile;
globalThis.AntennaPod = {"patchFile": patchFile}