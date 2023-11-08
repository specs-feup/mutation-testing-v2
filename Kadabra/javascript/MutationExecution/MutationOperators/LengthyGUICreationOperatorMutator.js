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
                // TODO: this placeholder logic should be extracted to an utility function that can be used by other operators
                
                // Cannot have code before super, insert placeholder statement, if not already there
                let leftSiblings = joinpoint.left; 
                
                let placeholder = undefined;

                // No siblings, insert placeholder
                if(leftSiblings.length === 0) {
                    placeholder = joinpoint.insertAfter("// AFTER SUPER");
					// Set line
					placeholder.line = joinpoint.line;
                } else {
                    const leftFirst = leftSiblings[0];
                    // Already inserted
                    if(leftFirst.code === "// AFTER SUPER")   {
                        placeholder = leftFirst;
                    } else {
                        placeholder = joinpoint.insertAfter("// AFTER SUPER");
						// Set line
						placeholder.line = joinpoint.line;						
                    }
                }
				
                //this.mutationPoints.push(joinpoint.call);
                //this.mutationPoints.push(joinpoint);                
                this.mutationPoints.push(placeholder);                                
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


        // Is a callStatement
        const code = this.mutationPoint.code + "\n" + codeSnippet;

        this.previousValue = this.mutationPoint;
        //this.mutationPoint = this.mutationPoint.insertAfter(codeSnippet);
        this.mutationPoint = this.mutationPoint.replaceWith(code);        
        this.currentIndex++;

        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");


    }
    _restorePrivate() {

        this.mutationPoint = this.mutationPoint.replaceWith(this.previousValue);
        //this.mutationPoint = this.mutationPoint.replaceWith("");
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
