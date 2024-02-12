laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "NotificationChannels.java") {
        // cahnnels must not be final, otherwise fully qualified names will not work
        Query.search("field", "channels").first().removeModifier("final");
    } else if(file.name === "IntroSlide5.java") {
        patchCallSection(file,"onActivityCreated")
    }
}

function patchCallSection(file, method) {
    println(Query.searchFrom(file, "method", method).first().ast);
        
    const prefix = "it.feio.android.omninotes.intro.IntroFragment.";
    const varName = "binding.";

    for(const jp of Query.searchFrom(file, "method", method).search("call", {"code" : c => c.startsWith(prefix + varName)})) {
            jp.replaceWith(jp.code.substring(prefix.length));
    }
}

globalThis.patchFile = patchFile;
globalThis.AntennaPod = {"patchFile": patchFile}