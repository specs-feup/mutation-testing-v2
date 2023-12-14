laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "DownloadsSection.java" || file.name === "SubscriptionsSection.java" || file.name === "EpisodesSurpriseSection.java" || file.name === "QueueSection.java" || file.name === "InboxSection.java" || file.name === "DownloadsSection.java") {
        //println(Query.search("method", "onCreateView").first().ast)
        patchViewBinding(file);
    }
    
    // NOVO TESTE
    if(file.name === "EpisodesSurpriseSection.java") {
        patchEpisodesSurpriseSection(file);
    }
    
}

// NOVA FUNÇÃO
function patchEpisodesSurpriseSection(file) {
        for(const jp of Query.searchFrom(file, "method", "onCreateView").search("var", {code: code => code.startsWith("de.danoeh.antennapod.ui.home.HomeSection.viewBinding")})) {
            jp.replaceWith("viewBinding")
        }
}

function patchViewBinding(file) {
    
    //method onCreateView
    for(const fieldAccess of Query.searchFrom(file, "method", "onCreateView").search("fieldAccess", {code: code => code.endsWith(".viewBinding")})) {
        const stmt = fieldAccess.ancestor("statement");
        const stmtCode = stmt.code.trim();

        if(stmtCode.startsWith("de.danoeh.antennapod.ui.home.HomeSection.viewBinding")) {
            stmt.replaceWith(stmtCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, stmtCode.length));                
        } else {
            const fieldAccessCode = fieldAccess.code;
            fieldAccess.replaceWith(fieldAccessCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, fieldAccessCode.length));
        }
        //println("Stmt: " + stmt.code);
    }

    //method onEventMainThread
    for(const fieldAccess of Query.searchFrom(file, "method", "onEventMainThread").search("fieldAccess", {code: code => code.endsWith(".viewBinding")})) {
        //println("FOUND!\n" + fieldAccess.code)
        const stmt = fieldAccess.ancestor("statement");
        const stmtCode = stmt.code.trim();

        if(stmtCode.startsWith("de.danoeh.antennapod.ui.home.HomeSection.viewBinding")) {
            stmt.replaceWith(stmtCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, stmtCode.length));                
        } else {
            const fieldAccessCode = fieldAccess.code;
            fieldAccess.replaceWith(fieldAccessCode.substring("de.danoeh.antennapod.ui.home.HomeSection.".length, fieldAccessCode.length));
        }
        //println("Stmt: " + stmt.code);
    }

    //method loadItems
    for(const fieldAccess of Query.searchFrom(file, "method", "loadItems").search("fieldAccess", {code: code => code.endsWith(".viewBinding")})) {
        //println("FOUND!\n" + fieldAccess.code)
        const stmt = fieldAccess.ancestor("statement");
        const stmtCode = stmt.code.trim();

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