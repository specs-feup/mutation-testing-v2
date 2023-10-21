laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class NullValueIntentPutExtraOperatorMutator extends Mutator {
    constructor() {
        super("NullValueIntentPutExtraOperatorMutator");

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

/*
        if (joinpoint.instanceOf("class") && this.addImport == false) {
            joinpoint.insertBefore(" import android.os.Parcelable;\n ");
            this.addImport = true;
        }
*/        
        if (joinpoint.instanceOf('callStatement') && joinpoint.call != undefined) {


            for (let i = 0; i < joinpoint.call.numChildren; i++) {


                if (joinpoint.call.children[i] != undefined && joinpoint.call.children[i].instanceOf('reference') && joinpoint.call.children[i].name != undefined && joinpoint.call.children[i].name === "putExtra") {
                    if (joinpoint.call.children[i + 2] != undefined) {
                        this.mutationPoints.push(joinpoint.call.children[i + 2]);
                        
                        joinpoint.ancestor("file").addImport("android.os.Parcelable");
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

        this.currentIndex++;

        this.previousValue = this.mutationPoint;

        this.mutationPoint = this.mutationPoint.insertReplace("new Parcelable[0]");


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
        return `Null Value Intent Put Extra Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
