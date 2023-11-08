laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class IntentTargetReplacementOperatorMutator extends Mutator {
    constructor() {
        super("IntentTargetReplacementOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.package = undefined;
        this.targetValues = [];
        this.allClassValues = [];
    }
    isAndroidSpecific() {
        return true;
    }
    addJp(joinpoint) {

        let potentialPoint = undefined;

        if (joinpoint.instanceOf('class')) {
            this.allClassValues.push(joinpoint.package + "-" + joinpoint.name + ".class");
        }

        if (joinpoint != undefined && joinpoint.type === "Intent" && joinpoint.instanceOf('expression') && !joinpoint.instanceOf('var') && !joinpoint.parent.instanceOf("if") && joinpoint.parent.type === undefined
        ) {
            if (joinpoint.children[0].name === "<init>" && joinpoint.children[0].type === "Executable") {
                //this.mutationPoints.push(joinpoint.children[2]);
                potentialPoint = joinpoint.children[2];

                if (joinpoint.children[2] !== undefined && joinpoint.children[2].type === 'Class' && joinpoint.children[2].children[1] != undefined && joinpoint.children[2].children[1].name === 'class' && joinpoint.children[2].children[1].type === 'Field') {
                    this.package = joinpoint.children[2].children[1].children[0].children[0].name;


                }
            }

            



            if (!(this.package === "undefined") && this.allClassValues.length > 0 && potentialPoint !== undefined) {


                for (let c of this.allClassValues) {
                    let getPackage = c.split("-");

                    println(getPackage[0] === this.package);

                    if (getPackage[0] === this.package && !this.targetValues.contains(getPackage[1])) {
                        {
                            this.targetValues.push(getPackage[1]);
                            println("Adding '"+getPackage[1]+"'")

                            // If it is the first one
                            if(this.targetValues.length === 1) {
                                this.mutationPoints.push(potentialPoint);
                            }
                        }

                    }
                }
            }
        }



        if (this.targetValues.length > 0) {
            //println("MORE THAN ZERO")
            return true;
        }
        //println("ZERO")
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
        //println("Target values: " + this.targetValues.length)
        const randomIndex = Math.floor(Math.random() * this.targetValues.length);

        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.currentIndex++;

        this.previousValue = this.mutationPoint;

        //println("INSERT: " + this.targetValues[randomIndex])
        this.mutationPoint = this.mutationPoint.insertReplace(this.targetValues[randomIndex]);


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
        return `Intent Target Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }

}
