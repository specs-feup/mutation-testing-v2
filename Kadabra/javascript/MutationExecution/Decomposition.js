laraImport("kadabra.KadabraNodes");

function changeVarDeclarations() {
  for (var jp of Query.root().descendants) {
    if (
      jp.instanceOf("localVariable") &&
      jp.init !== undefined &&
      !(jp.parent.type == "for") &&
      !jp.parent.instanceOf("try") &&
      // TODO: Add attribute in Kadabra to check if declaration is 'var', and use it instead of this
      // As an alternative, if var, replace var with the type
      !jp.code.startsWith("var ")
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