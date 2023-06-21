laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class StringArgumentReplacementOperatorMutator extends Mutator {
    constructor() {
        super("StringArgumentReplacementOperatorMutator");
        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }

    isAndroidSpecific() {
        return false;
    }

    addJp(joinpoint) {


        if (joinpoint != undefined && joinpoint.instanceOf('literal') && joinpoint.type == 'String' && joinpoint.parent != undefined && joinpoint.parent.type != undefined) {

            this.mutationPoints.push(joinpoint);

        } else {
            if (joinpoint != undefined && joinpoint.instanceOf('callStatement')) {
                if (joinpoint.call != undefined && joinpoint.call.children != undefined) {
                    for (const element of joinpoint.call.children) {

                        if (element.type == 'String' && !element.instanceOf('var')) {

                            for (let j = 0; j < element.numChildren; j++) {

                                if (element.children[j] != undefined && element.children[j].type == 'String') {

                                    this.mutationPoints.push(element.children[j]);

                                }
                            }
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

        this.mutationPoint = this.mutationPoints[this.currentIndex];



        this.previousValue = this.mutationPoint;


        this.mutationPoint = this.mutationPoint.insertReplace("\"\"");


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");

        this.currentIndex++;
    }

    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }


    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
    toString() {
        return `String Argument Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }
}
