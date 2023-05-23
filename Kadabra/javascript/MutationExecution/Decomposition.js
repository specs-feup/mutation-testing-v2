function changeVarDeclarations() {
  for (var jp of Query.root().descendants) {
    if (jp.instanceOf("localVariable") && jp.numChildren >= 2) {
      // Create write reference to local variable
      let localVar = KadabraNodes.var(jp, true);
      // Get initialization, and remove it from declaration
      const varLhs = jp.init;
      jp.init = undefined;
      // Create assignment
      const varAssign = KadabraNodes.assignment(localVar, varLhs);
      // Add assignment after initialization
      jp.insertAfter(varAssign);
    }
  }
}
