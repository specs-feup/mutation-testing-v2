laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "SubscriptionsSection.java") {
        // println(Query.search("method", "onEventMainThread").first().ast)
        patchViewBinding(file, "onCreateView");
    } else if(file.name === "DownloadsSection.java" 
        || file.name === "QueueSection.java") {
        patchViewBinding(file, "onCreateView");
        patchViewBinding(file, "onEventMainThread");
    } else if(file.name === "InboxSection.java" ) {
        patchViewBinding(file, "onCreateView");
        patchViewBinding(file, "loadItems");
    } else if(file.name === "EpisodesSurpriseSection.java") {
        // NOVO TESTE        
        patchEpisodesSurpriseSection(file, "onCreateView");
        patchViewBinding(file, "onCreateView");
        patchViewBinding(file, "onEventMainThread");

    }
}

function patchEpisodesSurpriseSection(file, method) {
    println(Query.searchFrom(file, "method", "onCreateView").first().ast);

    for(const jp of Query.searchFrom(file, "method", method).search("var", {code: code => code.startsWith("de.danoeh.antennapod.ui.home.HomeSection.viewBinding")})) {
        jp.replaceWith("viewBinding")
    }
}

function patchViewBinding(file, method) {
    //method onCreateView
    for(const fieldAccess of Query.searchFrom(file, "method", method).search("fieldAccess", {code: code => code.endsWith(".viewBinding")})) {
        //println("FOUND!\n" + fieldAccess.code)
        const stmt = fieldAccess.ancestor("statement");
        const stmtCode = stmt.code;

        if(stmtCode.startsWith("de.danoeh.antennapod.ui.home.HomeSection.viewBinding")) {
            println(method + " Original Stmt: " + stmtCode);
            stmt.replaceWith(stmtCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, stmtCode.length));
            println(method + " Replaced Stmt: " + stmt.code);
        } else {
            const fieldAccessCode = fieldAccess.code;
            println(method + " Original Field: " + fieldAccessCode);
            fieldAccess.replaceWith(fieldAccessCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, fieldAccessCode.length));
            println(method + " Replaced Field: " + fieldAccess.code);
        }
    }
}

globalThis.patchFile = patchFile;
globalThis.AntennaPod = {"patchFile": patchFile}