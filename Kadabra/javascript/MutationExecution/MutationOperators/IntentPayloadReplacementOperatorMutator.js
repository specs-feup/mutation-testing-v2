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

                    if (joinpoint.call.children[i + 2].typeReference == 'String') {
                        this.dataTypeOfSecondParam = "\"\"";

                    } else if (joinpoint.call.children[i + 2].typeReference == 'int' || joinpoint.call.children[i + 2].typeReference == 'float' || joinpoint.call.children[i + 2].typeReference == 'double' || joinpoint.call.children[i + 2].typeReference == 'long' || joinpoint.call.children[i + 2].typeReference == 'short' || joinpoint.call.children[i + 2].typeReference == 'short' || joinpoint.call.children[i + 2].typeReference == 'char' || joinpoint.call.children[i + 2].typeReference == 'byte') {
                        this.dataTypeOfSecondParam = "0";

                    } else if (joinpoint.call.children[i + 2].typeReference == 'boolean') {
                        this.dataTypeOfSecondParam = "true";

                    } else if (joinpoint.call.children[i + 2].typeReference == 'ArrayList') {
                        this.dataTypeOfSecondParam = "[]";
                    } else {
                        this.dataTypeOfSecondParam = "null";
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
