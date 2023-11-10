laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");
laraImport("lara.util.Random");

class InvalidMethodCallArgumentOperatorMutator extends Mutator {
    constructor(seed) {
        super("InvalidMethodCallArgumentOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.dataTypeOfSecondParam = undefined;
		this.random = new Random(seed);
    }


    isAndroidSpecific() {
        return false;
    }
    /*&&
            joinpoint.typeReference === "Intent" &&
            joinpoint.name === "<init>"
            && joinpoint.type === "Executable"
    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    addJp(joinpoint) {


        if (joinpoint.instanceOf('callStatement')) {


            if (joinpoint.call.children[1] != undefined) {
                if (joinpoint.call.children[1].instanceOf('var')) {
                    this.mutationPoints.push(joinpoint.call.children[1]);

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
        let randomValue = (this.random.next() + 1).toString(36).substring(2);
        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.currentIndex++;

        this.previousValue = this.mutationPoint;


        this.mutationPoint = this.mutationPoint.insertReplace("\"" + randomValue + "\"");


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


    }

    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }


    toString() {
        return `Invalid Method Call Argument Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
