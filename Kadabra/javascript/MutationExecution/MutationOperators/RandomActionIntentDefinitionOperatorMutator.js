laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class RandomActionIntentDefinitionOperatorMutator extends Mutator {
    constructor() {
        super("RandomActionIntentDefinitionOperatorMutator");

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
        if (
            $joinpoint.type === "Intent" && $joinpoint.instanceOf('expression') && !$joinpoint.instanceOf('var')
        ) {
            this.mutationPoints.push($joinpoint);
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

        let randomValue = (Math.random() + 1).toString(36).substring(7);

        this.mutationPoint = this.mutationPoints[this.currentIndex];
        this.currentIndex++;


        this.previousValue = this.mutationPoint.copy();


        this.mutationPoint = this.mutationPoint.insertReplace(randomValue);


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
        return `Random Action Intent Definition Operator Mutator from ${this.$original} to ${this.$expr}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
}
