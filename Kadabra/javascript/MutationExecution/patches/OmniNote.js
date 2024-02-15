laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "NotificationChannels.java" || 
        file.name === "ONStyle.java" ) {
        // cahnnels must not be final, otherwise fully qualified names will not work
        Query.search("field", "channels").first().removeModifier("final");
    } else if(file.name === "IntroSlide5.java" || 
        file.name === "IntroSlide1.java" || 
        file.name === "IntroSlide3.java" ||
        file.name === "IntroSlide6.java" ||
        file.name === "IntroSlide2.java" || 
        file.name === "IntroSlide4.java") {
        patchCallSection(file,"onActivityCreated")
    } 
}



function patchCallSection(file, method) {
    println(Query.searchFrom(file, "method", method).first().ast);
        
    const prefix = "it.feio.android.omninotes.intro.IntroFragment.";
    const varName = "binding.";

    for(const jp of Query.searchFrom(file, "method", method).search("call", {"code" : c => c.startsWith(prefix + varName)})) {
            jp.replaceWith(jp.code.substring(prefix.length)+";");
    }
}

globalThis.patchFile = patchFile;
globalThis.OmniNote = {"patchFile": patchFile}