laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Query");

class FindViewByIdDeletionMutator extends Mutator {
    //Parent constructor
    constructor() {
        super("FindViewByIdDeletionMutator")

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;

    }



    addJp($joinpoint) {
        if ($joinpoint.instanceOf('call') && $joinpoint.toString().includes("findViewById")) {
            this.mutationPoints.push($joinpoint);
            return true;
        }
        return false;
    }
    static _isCompatible = function (type1, type2) {
        return type1 === type2;
    }

    /*** IMPLEMENTATION OF INSTANCE METHODS ***/
    hasMutations() {
        return this.currentIndex < this.mutationPoints.length;
    }


    getMutationPoint() {
        if (this.currentIndex < this.mutationPoints.length) {
            return this.mutationPoints[this.currentIndex];
        } else {
            return undefined;
        }
    }
    _mutatePrivate() {

        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.previousValue = this.mutationPoint.copy;
        this.mutationPoint = this.mutationPoint.insertReplace("null");

        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");

    }

    _restorePrivate() {
        // Restore operator
        println("Restoring: " + this.mutationPoint + " to " + this.previousValue);
        this.mutationPoint.insertReplace(this.previousValue);
        this.currentIndex++;
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
}
