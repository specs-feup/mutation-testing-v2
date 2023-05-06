laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class InvalidDateOperatorMutator extends Mutator {
    constructor() {
        super("InvalidDateOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }

    /*&&
            $joinpoint.typeReference === "Intent" &&
            $joinpoint.name === "<init>"
            && $joinpoint.type === "Executable"
    /*** IMPLEMENTATION OF INSTANCE METHODS ***/

    addJp($joinpoint) {

        if ($joinpoint.type === "Date" && $joinpoint.instanceOf('localVariable') && !$joinpoint.instanceOf('var')
        ) {

            if ($joinpoint.children[0].name === "Date" && $joinpoint.children[1].instanceOf('new')) {


                this.mutationPoints.push($joinpoint.children[1]);

                debug(
                    "Adicionou um ponto de mutação " +
                    this.$expr +
                    " a " +
                    $joinpoint +
                    " na linha " +
                    $joinpoint.line
                );
                return true;
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

        let randomValue = Math.floor(Math.random() * 6553600);
        let s = "new Date(" + randomValue + ")";
        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.currentIndex++;

        this.previousValue = this.mutationPoint.copy();

        println(" this.originalParent" + this.previousValue);
        this.mutationPoint = this.mutationPoint.insertReplace(s);


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
        return `Invalid Date Operator Mutator from ${this.$original} to ${this.$expr}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
}
