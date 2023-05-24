laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class StringCallReplacementOperatorMutator extends Mutator {
    constructor() {
        super("StringCallReplacementOperatorMutator");

        this.mutationPointsTypeString = [];
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.nameOfmethodsOfJavaLang = ["length", "charAt", "substring", "startsWith", "endsWith", "toUpperCase", "toLowerCase"];
        this.methodsOfJavaLang0arguments = ["length()", "toUpperCase()", "toLowerCase()"];
        this.methodsOfJavaLang1arguments = ["charAt", "startsWith", "endsWith", "contains"];

    }
    /* 
           Information From chatGpt
                  String class methods:
                      length()
                      charAt(int index)
                      substring(int beginIndex, int endIndex)
                      startsWith(String prefix)
                      endsWith(String suffix)
                      toUpperCase()
                      toLowerCase() */

    addJp(joinpoint) {

        if (joinpoint.instanceOf('callStatement')) {


            for (let i = 0; i < joinpoint.call.children.length; i++) {

                if (joinpoint.call.children[i].instanceOf('reference') && joinpoint.call.children[i].type === "Executable") {
                    if (this.nameOfmethodsOfJavaLang.contains(joinpoint.call.children[i].name)) {
                        if (joinpoint.call.numChildren == 2) {
                            //joinpoint
                            this.mutationPoints.push(joinpoint.call);
                            //string
                            this.mutationPointsTypeString.push(joinpoint.call + ";" + joinpoint.call.children[i].name);
                        }
                        if (joinpoint.call.numChildren == 3) {
                            //joinpoint
                            this.mutationPoints.push(joinpoint.call);
                            //string
                            this.mutationPointsTypeString.push(joinpoint.call + ";" + joinpoint.call.children[i].name + ";" + joinpoint.call.children[i + 1]);

                        }
                        if (joinpoint.call.numChildren == 4) {
                            //joinpoint
                            this.mutationPoints.push(joinpoint.call);
                            //string
                            this.mutationPointsTypeString.push(joinpoint.call + ";" + joinpoint.call.children[i].name + ";" + joinpoint.call.children[i + 1] + ";" + joinpoint.call.children[i + 2]);
                        }

                    }

                }
            }


        }
        if (this.mutationPoints.length > 0) {
            return true;
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

        let randomIndex = Math.floor(Math.random() * this.methodsOfJavaLang0arguments.length);
        let randomIndex1 = Math.floor(Math.random() * this.methodsOfJavaLang1arguments.length);


        this.mutationPoint = this.mutationPoints[this.currentIndex];
        this.mutationPointString = this.mutationPointsTypeString[this.currentIndex];

        this.previousValue = this.mutationPoint;

        if (this.mutationPointString.split(";").length == 2) {

            while (this.methodsOfJavaLang0arguments[randomIndex] == (this.mutationPointString.split(";")[1] + "()")) {
                randomIndex = Math.floor(Math.random() * this.methodsOfJavaLang0arguments.length);
            }
            this.mutationPoint = this.mutationPoint.insertReplace(this.mutationPointString.split(";")[0].replace(this.mutationPointString.split(";")[1] + "()", this.methodsOfJavaLang0arguments[randomIndex]));
        } else if (this.mutationPointString.split(";").length == 3) {
            if (Number.isInteger(parseInt(this.mutationPointString.split(";")[2]))) {
                this.mutationPoint = this.mutationPoint.insertReplace(this.mutationPointString.split(";")[0].replace(this.mutationPointString.split(";")[1] + "(" + this.mutationPointString.split(";")[2] + ")", this.methodsOfJavaLang0arguments[randomIndex]));

            } else if ((typeof this.mutationPointString.split(";")[2]) === "string") {

                while ((this.methodsOfJavaLang1arguments[randomIndex1] == (this.mutationPointString.split(";")[1]) || (this.methodsOfJavaLang1arguments[randomIndex1] == "charAt"))) {
                    randomIndex1 = Math.floor(Math.random() * this.methodsOfJavaLang1arguments.length);
                }
                this.mutationPoint = this.mutationPoint.insertReplace(this.mutationPointString.split(";")[0].replace(this.mutationPointString.split(";")[1] + "(" + '"' + this.mutationPointString.split(";")[2] + '"' + ")", this.methodsOfJavaLang1arguments[randomIndex1] + "(" + '"' + this.mutationPointString.split(";")[2] + '"' + ")"));

            }

        } else if (this.mutationPointString.split(";").length == 4) {
            this.mutationPoint = this.mutationPoint.insertReplace(this.mutationPointString.split(";")[0].replace((this.mutationPointString.split(";")[1] + "(" + this.mutationPointString.split(";")[2] + ", " + this.mutationPointString.split(";")[3] + ")"), this.methodsOfJavaLang0arguments[randomIndex]));

        }



        this.currentIndex++;



        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");

    }

    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
    }


    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
    toString() {
        return `String Call Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }
}
