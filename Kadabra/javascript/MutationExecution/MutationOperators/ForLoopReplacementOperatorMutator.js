laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class ForLoopReplacementOperatorMutator extends Mutator {
    constructor() {
        super("ForLoopReplacementOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.forStatement = undefined;
        this.maxValue = -1;
        this.classTypeOfIDeclaration = undefined;
    }

    isAndroidSpecific(){
      return false;
    }
    addJp(joinpoint) {


        if ((joinpoint.parent.type == "for")
        ) {

            this.forStatement = joinpoint.parent;

            if (this.maxValue == -1) {
                if (joinpoint.instanceOf('expression') && joinpoint.type == "boolean" && joinpoint.children[2].instanceOf('literal')) {
                    this.maxValue = joinpoint.children[2];
                    println("if1" + this.maxValue);

                }

            }

            if (joinpoint.instanceOf('statement') && joinpoint.instanceOf("localVariable")) {

                println("if2: " + this.maxValue);
                const iDeclaration = joinpoint.copy().toString();
                this.classTypeOfIDeclaration = iDeclaration.split("=")[0];
                if (joinpoint.children[1].instanceOf('literal')) {
                    this.mutationPoints.push(joinpoint);
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
        const number = this.mutationPoint.toString().split("=")[1].trim();

        if (this.maxValue == -1 && number == 0) {
            this.maxValue = 1;
        }

        var randomNumber = Math.floor(Math.random() * (this.maxValue - 0 + 1)) + 0;

        this.previousValue = this.forStatement.toString();

        if ((number.trim() === randomNumber.toString().trim())) {
            randomNumber = Math.floor(Math.random() * this.maxValue);

        };

        this.mutationPoint = this.mutationPoint.insertReplace(this.forStatement.toString().replace(this.mutationPoint.toString(), (this.classTypeOfIDeclaration + "= " + randomNumber.toString())));




        this.currentIndex++;
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
        return `For Loop Replacement Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {

        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
