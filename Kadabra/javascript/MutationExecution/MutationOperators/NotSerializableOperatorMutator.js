laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class NotSerializableOperatorMutator extends Mutator {
    constructor() {
        super("NotSerializableOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
        this.removedInterface = undefined;
    }
    
    isAndroidSpecific(){
      return false;
    }

    addJp(joinpoint) {
        // println("joinpoint   " + joinpoint.interfaces);


        // println("joinpoint   " + joinpoint.superClassJp);


        //const aClass = Query.search("class", "LoginActivity").first();
        //println(aClass.removeInterface("java.io.Serializable"));




        if (joinpoint.instanceOf("class")) {
            if (joinpoint.interfaces.contains("java.io.Serializable")) {
                this.mutationPoints.push(joinpoint);
                return true;
            }
        }
        return false;
    }
    hasMutations() {
        return this.currentIndex < this.mutationPoints.length;
    }

    getMutationPoint() {

        if (this.isMutated) {
            println("this.isMutated   " + this.mutationPoint);
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
        println("this.previousValue   " + this.previousValue);

        this.removedInterface = this.mutationPoint.removeInterface("java.io.Serializable");

        println("this.removedInterface   " + this.removedInterface);


        println("/*--------------------------------------*/");
        println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
            + " to " + "\"\"");
        println("/*--------------------------------------*/");


        println(" this.mutationPoint" + this.mutationPoint);
    }

    _restorePrivate() {
        this.mutationPoint = this.mutationPoint.addInterface(this.removedInterface);
        this.previousValue = undefined;
        this.mutationPoint = undefined;

    }


    toString() {
        return `Not Serializable Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }
    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        }
    }

}
