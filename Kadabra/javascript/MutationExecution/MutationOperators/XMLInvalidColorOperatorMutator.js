laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class XMLInvalidColorOperatorMutator extends Mutator {
    constructor() {
        super("XMLInvalidColorOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.parentPoint = undefined;
        this.nameOfFileMutated = undefined;
        this.nameOfFileToMutate = undefined;
        this.colors = [];
        this.increment = 1;
        this.destinationPath = undefined;
    }

    isAndroidSpecific() {
        return true;
    }
    readAndCopyXmlFile(xmNameFile) {
        const path = projectPath + "/main/res/layout/" + xmNameFile + ".xml";
        this.nameOfFileMutated = xmNameFile + "_" + this.increment;
        this.destinationPath = projectPath + "/main/res/layout/" + this.nameOfFileMutated + ".xml";

        while (Io.isFile(this.destinationPath)) {
            this.increment++;
            this.nameOfFileMutated = xmNameFile + "_" + this.increment;
            this.destinationPath = projectPath + "/main/res/layout/" + this.nameOfFileMutated + ".xml";

        };
        Io.copyFile(path, this.destinationPath);
        const xmlFileContent = Io.readFile(this.destinationPath);

        this.increment++;
        return xmlFileContent;
    }



    addJp(joinpoint) {

        if (joinpoint.instanceOf("callStatement") && joinpoint.call.instanceOf("expression")) {
            for (var point of joinpoint.call.descendants) {
                if (this.parentPoint == undefined) {
                    if (point.instanceOf("reference") && point == "setContentView - Executable") {
                        this.parentPoint = point.parent;

                    }

                    if (this.parentPoint != undefined) {

                        if (this.parentPoint.instanceOf("expression")) {

                            if (this.parentPoint.instanceOf("expression")) {
                                this.nameOfFileToMutate = this.parentPoint.children[1];

                            }
                            if (this.mutationPoints.length < 0 || !(this.mutationPoints.contains(this.nameOfFileToMutate))) {
                                this.mutationPoints.push(this.nameOfFileToMutate);

                                let randomIndex = 0;
                                if (this.nameOfFileToMutate != undefined) {
                                    var xmlFileContent = this.readAndCopyXmlFile(this.nameOfFileToMutate);
                                    var root = KadabraNodes.xmlNode(xmlFileContent);

                                    //XML PART
                                    for (let textColor of Query.searchFrom(root, "xmlElement")) {
                                        if (textColor.attribute("android:textColor") != "" && !this.colors.contains(textColor.attribute("android:textColor"))) {
                                            this.colors.push(textColor.attribute("android:textColor"));
                                            this.colors.push("#FF0000");
                                            this.colors.push("#FF00FF");
                                            this.colors.push("#00FF00");
                                            this.colors.push("#FFA500");
                                            this.colors.push("#0000FF");
                                        }
                                    }
                                    if (this.colors.length > 0) {
                                        randomIndex = Math.floor(Math.random() * this.colors.length);
                                        for (let textColor of Query.searchFrom(root, "xmlElement")) {
                                            if (textColor.attribute("android:textColor")) {
                                                while (textColor.attribute("android:textColor") == this.colors[randomIndex]) {
                                                    randomIndex = Math.floor(Math.random() * this.colors.length);
                                                }
                                                textColor.setAttribute("android:textColor", this.colors[randomIndex]);
                                                Io.writeFile(this.destinationPath, root.srcCode);
                                                return true;
                                            }
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
            }




        }
        return false;
    }


    hasMutations() {
        return this.currentIndex < this.mutationPoints.length;
    }

    getMutationPoint() {
        if (this.isMutated) {
            return this.mutationPoint;
        } else {
            if (this.currentIndex < this.mutationPoints.length) {
                return this.mutationPoints[this.currentIndex];
            } else {
                return undefined;
            }
        }
    }

    _mutatePrivate() {
        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.previousValue = this.mutationPoint;

        this.mutationPoint = this.mutationPoint.insertReplace(this.nameOfFileMutated);





        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


        this.currentIndex++;


    }

    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `XML Invalid Color Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
