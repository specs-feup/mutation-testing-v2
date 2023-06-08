class Arguments {
  constructor(
    outputFolder,
    args,
    workspaceFolder,
    traditionalMutation,
    includesFolder,
    classpath,
    useIncompleteClassPath
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
    this.classpath = classpath;
    this.useIncompleteClassPath = useIncompleteClassPath;
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
    if (this.useIncompleteClassPath) {
      strAux.push("-X");
    }

    //classpath
    strAux.push("-I");
    strAux.push(this.classpath);

    //remove logs
    strAux.push("-b");
    strAux.push("2");

    /*strAux.push("-I");
    strAux.push(
      "/home/david/Desktop/Projects/java-jwt/lib/build/classes/java/main;/home/david/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.14.2/a7aae9525864930723e3453ab799521fdfd9d873/jackson-annotations-2.14.2.jar;/home/david/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.14.2/f804090e6399ce0cf78242db086017512dd71fcc/jackson-core-2.14.2.jar;/home/david/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.14.2/1e71fddbc80bb86f71a6345ac1e8ab8a00e7134/jackson-databind-2.14.2.jar"
    );*/

    return strAux;
  }
}
