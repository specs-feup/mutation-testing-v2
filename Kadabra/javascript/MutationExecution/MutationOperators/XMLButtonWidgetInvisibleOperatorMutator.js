laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class XMLButtonWidgetInvisibleOperatorMutator extends Mutator {
    constructor(rootPath) {
        super("XMLButtonWidgetInvisibleOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;

        this.parentPoint = undefined;
        this.nameOfFileMutated = undefined;
        this.nameOfFileToMutate = undefined;
        this.increment = 1;
        this.destinationPath = undefined;
        this.rootPath = rootPath;
    }

    isAndroidSpecific() {
        return true;
    }
    readAndCopyXmlFile(xmNameFile) {
        const path = this.rootPath + xmNameFile + ".xml";
        this.nameOfFileMutated = xmNameFile + "_" + this.increment;
        this.destinationPath = this.rootPath + this.nameOfFileMutated + ".xml";

        while (Io.isFile(this.destinationPath)) {
            this.increment++;
            this.nameOfFileMutated = xmNameFile + "_" + this.increment;
            this.destinationPath = this.rootPath + this.nameOfFileMutated + ".xml";

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
                                let mutated = false;
                                if (this.nameOfFileToMutate != undefined) {

                                    var xmlFileContent = this.readAndCopyXmlFile(this.nameOfFileToMutate);
                                    var root = KadabraNodes.xmlNode(xmlFileContent);

                                    //XML PART
                                    for (let button of Query.searchFrom(root, "xmlElement")) {
                                        let src = root.srcCode;
                                        if (button.name == "Button") {

                                            if (button.attribute("android:visibility") != "" && button.attribute("android:visibility") != "invisible") {
                                                button.setAttribute("android:visibility", "invisible");
                                                src = root.srcCode;
                                                mutated = true;
                                            } else if (button.attribute("android:visibility") == "invisible") { }
                                            else {
                                                let id = button.attribute("android:id");
                                                let buttonString = "android:id=\"" + id + "\"";
                                                src = src.replace(buttonString, buttonString + "\n" + " android:visibility=\"invisible\"");
                                                mutated = true;
                                            }
                                        }
                                        Io.writeFile(this.destinationPath, src);

                                        if (mutated) {
                                            return true;
                                        }

                                    }
                                    if (mutated) {
                                        return true;
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
        return `XML Button Widget Invisible Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
