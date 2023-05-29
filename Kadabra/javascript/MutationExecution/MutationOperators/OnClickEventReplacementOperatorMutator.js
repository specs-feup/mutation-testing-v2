laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class OnClickEventReplacementOperatorMutator extends Mutator {
    constructor() {
        super("OnClickEventReplacementOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific(){
      return true;
    }

    addJp(joinpoint) {

        if (
            joinpoint.type === "OnClickListener"
        ) {
            for (let i = 0; i < joinpoint.numChildren; i++) {
                for (let j = 0; j < joinpoint.children[i].numChildren; j++) {
                    if (joinpoint.children[i].children[j].instanceOf('method')) {
                        for (let k = 0; k < joinpoint.children[i].children[j].numChildren; k++) {
                            if (joinpoint.children[i].children[j].children[k].instanceOf('body')) {
                                let numChildren = joinpoint.children[i].children[j].children[k].numChildren;
                                this.mutationPoints.push(joinpoint.children[i].children[j].children[k].children[numChildren - 1]);
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
        println("this.mutationPoint: " + this.mutationPoint);
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
        return `On Click Event Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: []
            ,
            operator: this.name,
        };
    }
}
