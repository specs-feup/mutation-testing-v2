laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");
laraImport("lara.util.Random");

class InvalidDateOperatorMutator extends Mutator {
    constructor(seed) {
        super("InvalidDateOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
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

        if (joinpoint != undefined && joinpoint.type === "Date" && joinpoint.instanceOf('localVariable') && !joinpoint.instanceOf('var')
        ) {

            if (joinpoint.children[0] != undefined && joinpoint.children[1] != undefined && joinpoint.children[0].name === "Date" && joinpoint.children[1].instanceOf('new')) {


                this.mutationPoints.push(joinpoint.children[1]);

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

        let randomValue = Math.floor(this.random.next() * 6553600);
        let s = "new Date(" + randomValue + ")";
        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.currentIndex++;

        this.previousValue = this.mutationPoint;

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
        return `Invalid Date Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
