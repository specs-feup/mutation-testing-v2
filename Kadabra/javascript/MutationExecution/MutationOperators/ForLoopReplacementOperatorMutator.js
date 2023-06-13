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

        this.classTypeOfIDeclaration = undefined;
    }

    isAndroidSpecific() {
        return false;
    }
    addJp(joinpoint) {


        if ((joinpoint.parent.type == "for")
        ) {

            this.forStatement = joinpoint.parent;


            if (joinpoint.instanceOf('statement') && joinpoint.instanceOf("localVariable")) {

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

        var randomNumber = Math.floor(Math.random() * (100)) + 1;

        this.previousValue = this.forStatement.toString();


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
