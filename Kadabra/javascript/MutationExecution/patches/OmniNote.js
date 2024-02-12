laraImport("weaver.Query")

/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "NotificationChannels.java") {
        patchAnonymousExecFieldAccess(file, "NotificationChannels");
    } 
}

function patchAnonymousExecFieldAccess(file, clazz) {
    //method onCreateView
    for(const fieldAccess of Query.searchFrom(file, "class", clazz).search("fieldAccess", {code: code => code.endsWith(".channels")})) {
        println("FOUND!\n" + fieldAccess.code)
        const stmt = fieldAccess.ancestor("assignment");
        const stmtCode = stmt.code;

        if(stmtCode.startsWith("it.feio.android.omninotes.helpers.notifications.NotificationChannels.channels")) {
            println(clazz + " Original Stmt: " + stmtCode);
            stmt.replaceWith(stmtCode.substring("it.feio.android.omninotes.helpers.notifications.NotificationChannels.".length, stmtCode.length));
            println(clazz + " Replaced Stmt: " + stmt.code);
        } else {
            const fieldAccessCode = fieldAccess.code;
            println(clazz + " Original Field: " + fieldAccessCode);
            fieldAccess.replaceWith(fieldAccessCode.substring("it.feio.android.omninotes.helpers.notifications.NotificationChannels.".length, fieldAccessCode.length));
            println(clazz + " Replaced Field: " + fieldAccess.code);
        }
    }
}

function patchBinding(file, method) {
    //method onCreateView
    for(const fieldAccess of Query.searchFrom(file, "method", method).search("fieldAccess", {code: code => code.endsWith(".binding.introBackground.setBackgroundColor")})) {
        println("FOUND!\n" + fieldAccess.code)
        const stmt = fieldAccess.ancestor("statement");
        const stmtCode = stmt.code;

        if(stmtCode.startsWith("it.feio.android.omninotes.intro.IntroFragment.binding.introBackground.setBackgroundColor")) {
            println(method + " Original Stmt: " + stmtCode);
            stmt.replaceWith(stmtCode.substring("it.feio.android.omninotes.intro.IntroFragment.".length, stmtCode.length));
            println(method + " Replaced Stmt: " + stmt.code);
        } else {
            const fieldAccessCode = fieldAccess.code;
            println(method + " Original Field: " + fieldAccessCode);
            fieldAccess.replaceWith(fieldAccessCode.substring("it.feio.android.omninotes.intro.IntroFragment.".length, fieldAccessCode.length));
            println(method + " Replaced Field: " + fieldAccess.code);
        }
    }
}

globalThis.patchFile = patchFile;
globalThis.AntennaPod = {"patchFile": patchFile}