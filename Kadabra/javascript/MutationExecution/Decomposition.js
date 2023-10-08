function changeVarDeclarations() {
  for (var jp of Query.root().descendants) {
    if (
      jp.instanceOf("localVariable") &&
      jp.numChildren >= 2 &&
      !(jp.parent.type == "for") &&
      !jp.parent.instanceOf("try")
    ) {
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

// To avoid a warning, and follow the convention that a JS file of a given name exposes a variable with the same name
const Decomposition = {};
Decomposition.changeVarDeclarations = changeVarDeclarations;