laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class InvalidViewFocusOperatorMutator extends Mutator {
    constructor() {
        super("InvalidViewFocusOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific(){
      return true;
    }
    addJp(joinpoint) {

        if (joinpoint.instanceOf('call')
        ) {

            if (joinpoint.children[0] == 'findViewById - Executable') {

                this.mutationPoints.push(joinpoint.parent);
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
        let variable = this.mutationPoint.toString().split("=")[0].trim();

        let toReplaceWith = variable + ".requestFocus()"
        this.currentIndex++;

        this.previousValue = this.mutationPoint;
        this.mutationPoint = this.mutationPoint.insertAfter(toReplaceWith);


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


    }
    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.replaceWith("");
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Invalid  View Focus Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: []
            ,
            operator: this.name,
        };
    }
}
