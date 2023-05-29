class Arguments {
  constructor(
    outputFolder,
    args,
    workspaceFolder,
    traditionalMutation,
    includesFolder
  ) {
    this.writeCode = false;
    this.outputFolder = outputFolder;

    if (traditionalMutation) {
      this.javascriptFile = (
        includesFolder +
        Io.getSeparator() +
        "MutationExecution" +
        Io.getSeparator() +
        "TraditionalMutation.js"
      ).trim();
    } else {
      this.javascriptFile = (
        includesFolder +
        Io.getSeparator() +
        "MutationExecution" +
        Io.getSeparator() +
        "MutantSchemata.js"
      ).trim();
    }

    this.includesFolder = (
      includesFolder +
      Io.getSeparator() +
      "MutationExecution"
    ).trim();
    this.stackTrace = false;
    this.externalDependencies = [];
    this.debugMode = false;
    this.args = args;
    this.fullyQualifiedNames = false;
    this.workspaceFolder = workspaceFolder;
  }

  addDependencies(url) {
    externalDependencies.push(url);
  }

  getList() {
    //Add entry point
    let strAux = [];

    //strAux.push("clava ");
    strAux.push(this.javascriptFile);

    //Clava with stack strace
    if (this.stackTrace) {
      strAux.push("-s");
    }

    //Writes the code by it selfy
    if (this.writeCode) {
      strAux.push("WC");
    }

    //Adds the output path
    strAux.push("-o");
    strAux.push(this.outputFolder);

    if (this.debugMode) {
      strAux.push("-d");
    }

    //Adds the dependencies (pode ser preciso remover o ultimo; )
    if (this.externalDependencies.length > 0) {
      strAux.push("-dep ");

      let aux = "";
      for (i in this.externalDependencies) {
        aux += i + ";";
      }
      strAux.push(aux);
    }

    //add args
    strAux.push("-av");
    strAux.push(this.args);

    //Fully qualified names
    if (this.fullyQualifiedNames) {
      strAux.push("-Q");
    }

    //includes folder
    strAux.push("-i");
    strAux.push(this.includesFolder);

    //files
    strAux.push("-p");
    strAux.push(this.workspaceFolder);

    //Incomplete classpath
    strAux.push("-X");

    return strAux;
  }
}
