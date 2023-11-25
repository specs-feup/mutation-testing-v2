/**
 * Specific patches for the Aegis project.
 * 
 * @param {file} file 
 */
function patchFile(file) {
    if(file.name === "BattleNetImporter.java") {
        // _key must not be final, otherwise fully qualified names will not work
      Query.search("field", "_key").first().removeModifier("final");
    }
  
    if(file.name === "EditEntryActivity.java") {
      var call = Query.searchFrom(file, "call", "showTextInputDialog").first();
      call.setArgument(
        KadabraNodes.snippetExpr(
          "com.beemdevelopment.aegis.ui.EditEntryActivity.this"
        ),
        0
      );
    }
  
    if(file.name === "MainActivity.java") {
      var call = Query.searchFrom(file, "call", "showDeleteEntriesDialog").first();
      call.setArgument(KadabraNodes.snippetExpr("com.beemdevelopment.aegis.ui.MainActivity.this"), 0);
    }
}

globalThis.patchFile = patchFile;
globalThis.Aegis = {"patchFile": patchFile}