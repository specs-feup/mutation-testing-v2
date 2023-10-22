laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class IntentPayloadReplacementOperatorMutator extends Mutator {
    constructor() {
        super("IntentPayloadReplacementOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.dataTypeOfSecondParam = undefined;
    }

    isAndroidSpecific(){
      return true;
    }
    /*&&
            joinpoint.typeReference === "Intent" &&
            joinpoint.name === "<init>"
            && joinpoint.type === "Executable"
    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp(joinpoint) {


        if (joinpoint.instanceOf('callStatement')) {


            for (let i = 0; i < joinpoint.call.children.length; i++) {


                if (joinpoint.call.children[i].instanceOf('reference') && joinpoint.call.children[i].name === "putExtra") {
                    this.mutationPoints.push(joinpoint.call.children[i + 2]);

                    //const type = joinpoint.call.children[i + 2].typeReference;
                    const type = joinpoint.call.children[i + 2].type;

                    // ToDo: missing case for array type

                    if (type === 'String') {
                        this.dataTypeOfSecondParam = "\"\"";

                    } else if (type === 'int' || type === 'float' || type === 'double' || type === 'long') {
                        this.dataTypeOfSecondParam = "0";
                    } else if (type === 'short') {
                        this.dataTypeOfSecondParam = "(short) 0";

                    } else if (type === 'byte') {
                        this.dataTypeOfSecondParam = "(byte) 0";

                    } else if (type === 'char') {
                        this.dataTypeOfSecondParam = "'0'";

                    } else if (type === 'boolean') {
                        this.dataTypeOfSecondParam = "true";

                    // Wrong, ArrayList is object    
                    //} else if (type == 'ArrayList') {
                    //    println("Type: " + joinpoint.call.children[i + 2].type)
                    //    this.dataTypeOfSecondParam = "[]";
                    } else {
                        this.dataTypeOfSecondParam = "(" + type + ") null";
                    }

                    debug(
                        "Adicionou um ponto de mutação " +
                        this.$expr +
                        " a " +
                        joinpoint +
                        " na linha " +
                        joinpoint.line
                    );
                    return true;
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

        this.currentIndex++;

        this.previousValue = this.mutationPoint.copy();

        this.mutationPoint = this.mutationPoint.insertReplace(this.dataTypeOfSecondParam);


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


        println(" this.mutationPoint" + this.mutationPoint);
    }

    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }


    toString() {
        return `Intent Payload Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }

}
