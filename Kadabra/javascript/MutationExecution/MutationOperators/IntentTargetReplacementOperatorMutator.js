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

    addJp($joinpoint) {

        if ($joinpoint.instanceOf('class')) {
            this.allClassValues.push($joinpoint.package + "-" + $joinpoint.name + ".class");
        }

        if ($joinpoint.type === "Intent" && $joinpoint.instanceOf('expression') && !$joinpoint.instanceOf('var')
        ) {
            if ($joinpoint.children[0].name === "<init>" && $joinpoint.children[0].type === "Executable") {
                this.mutationPoints.push($joinpoint.children[2]);


                if ($joinpoint.children[2].type === 'Class' && $joinpoint.children[2].children[1].name === 'class' && $joinpoint.children[2].children[1].type === 'Field') {
                    this.package = $joinpoint.children[2].children[1].children[0].children[0].name;


                }
            }

            //



            if (!(this.package === "undefined") && this.allClassValues.length > 0) {


                for (let c of this.allClassValues) {
                    let getPackage = c.split("-");

                    println(getPackage[0] === this.package);

                    if (getPackage[0] === this.package && !this.targetValues.contains(getPackage[1])) {
                        {
                            this.targetValues.push(getPackage[1]);

                        }

                    }
                }
            }
        }



        if (this.targetValues.length > 0) {
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

        const randomIndex = Math.floor(Math.random() * this.targetValues.length);

        this.mutationPoint = this.mutationPoints[this.currentIndex];

        this.currentIndex++;

        this.previousValue = this.mutationPoint.copy();

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
        return `Intent Target Replacement Operator Mutator from ${this.$original} to ${this.$expr}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previoues value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
        };
    }
}
