laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class NullValueIntentOperatorMutator extends Mutator {
    constructor() {
        super("NullValueIntentOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.addImport = false;
    }
    isAndroidSpecific() {
        return true;
    }
    /*&&
            joinpoint.typeReference === "Intent" &&
            joinpoint.name === "<init>"
            && joinpoint.type === "Executable"
    /*** IMPLEMENTATION OF INSTANCE METHODS ***/

    addJp(joinpoint) {

        if (joinpoint.instanceOf("class") && this.addImport == false) {
            joinpoint.insertBefore(" import android.os.Parcelable;\n ");
            this.addImport = true;
        }
        if (joinpoint != undefined && joinpoint.parent != undefined && joinpoint.type === "Intent" && joinpoint.instanceOf('expression') && !joinpoint.instanceOf('var') && !joinpoint.parent.instanceOf('var') && joinpoint.parent.type === undefined
        ) {
            if (joinpoint.children[0] != undefined && joinpoint.children[0].name === "<init>" && joinpoint.children[0].type === "Executable") {

                if (joinpoint.children[2] != undefined) {
                    this.mutationPoints.push(joinpoint.children[2]);

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


        this.previousValue = this.mutationPoint;

        this.mutationPoint = this.mutationPoint.insertReplace("new Parcelable[0]");


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + this.mutationPoint);
        println("/*--------------------------------------*/");
        this.currentIndex++;

        println(" this.mutationPoint" + this.mutationPoint);
    }



    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);
        this.previousValue = undefined;
        this.mutationPoint = undefined;
    }

    toString() {
        return `Null Value Intent Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
