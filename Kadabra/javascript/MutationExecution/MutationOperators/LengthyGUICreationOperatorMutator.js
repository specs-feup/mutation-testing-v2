laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class LengthyGUICreationOperatorMutator extends Mutator {
    constructor() {
        super("LengthyGUICreationOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific(){
      return true;
    }
    addJp(joinpoint) {

        if (joinpoint.instanceOf('callStatement')
        ) {
            if (joinpoint.call.children[0] == 'super' && joinpoint.call.children[1] == 'onCreate - Executable') {
                this.mutationPoints.push(joinpoint.call);
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
        let codeSnippet = "try { Thread.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }";




        this.previousValue = this.mutationPoint;
        this.mutationPoint = this.mutationPoint.insertAfter(codeSnippet);
        this.currentIndex++;

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
        return `Lengthy GUI Creation Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: []
            ,
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
