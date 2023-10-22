laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class LengthyGUIListenerOperatorMutator extends Mutator {
    constructor() {
        super("LengthyGUIListenerOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
    }
    isAndroidSpecific(){
      return true;
    }
    addJp(joinpoint) {
        // ToDo: Previous way of determining if join point should be mutated entered in conflict with BuggyGUIListenerOperatorMutator,
        // because mutation point was to "far away" from the join point being tested
        // As a rule of thumb, it is ok for the mutation point to not be the current join point as long as they are confined to the same statement
        // This restriction can lifted ifOperators are adapted to return the point mutation point, and MutantSchemata is adapted to use this information  

        if (
            //joinpoint.type === "OnClickListener"
            joinpoint.instanceOf('body')
        ) {
            if(joinpoint.parent !== undefined && joinpoint.parent.instanceOf("method") && joinpoint.parent.parent !== undefined && joinpoint.parent.parent.parent !== undefined && joinpoint.parent.parent.parent.type === "OnClickListener") {

                        let numChildren = joinpoint.numChildren;
                        if(numChildren > 0) {
                            // Mutation point is a statement
                            this.mutationPoints.push(joinpoint.children[numChildren - 1]);
                            
                            // ToDo: Currently MutantSchemata only supports one mutation per operator per point
                            return true;
                        }
            }
            /*
            for (let i = 0; i < joinpoint.numChildren; i++) {
                for (let j = 0; j < joinpoint.children[i].numChildren; j++) {
                    if (joinpoint.children[i].children[j].instanceOf('method')) {
                        for (let k = 0; k < joinpoint.children[i].children[j].numChildren; k++) {
                            if (joinpoint.children[i].children[j].children[k].instanceOf('body')) {
                                let numChildren = joinpoint.children[i].children[j].children[k].numChildren;
                                if(numChildren > 0) {
                                    // Mutation point is a statement
                                    this.mutationPoints.push(joinpoint.children[i].children[j].children[k].children[numChildren - 1]);
                                    
                                    // ToDo: Currently MutantSchemata only supports one mutation per operator per point
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            */
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

        // Is a statement
        const code = this.mutationPoint.code + "\n" + codeSnippet;


        this.previousValue = this.mutationPoint;
        this.mutationPoint = this.mutationPoint.replaceWith(code);


        this.currentIndex++;
        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


    }
    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.replaceWith(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Lengthy GUI Listener Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
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
