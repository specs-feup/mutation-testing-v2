laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");

class InvalidKeyIntentOperatorMutator extends Mutator {
    constructor() {
        super("InvalidKeyIntentOperatorMutator");

        this.mutationPoints = [];
        this.currentIndex = 0;
        this.mutationPoint = undefined;
        this.previousValue = undefined;
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


        if (joinpoint.type === "Intent" && joinpoint.instanceOf('expression') && !joinpoint.instanceOf('var') && !joinpoint.parent.instanceOf("if") && joinpoint.parent.type === undefined && joinpoint!=undefined
        ) {
            if (joinpoint.children[0].name === "<init>" && joinpoint.children[0].type === "Executable") {
                if (joinpoint.children[1] != undefined) {
                    this.mutationPoints.push(joinpoint.children[1]);
                }
            }


        } if (this.mutationPoints.length > 0) {
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

		
        // ToDo: there is duplicated code below that also is in ConstructorCallOperatorMutator, refactor
        // Although it is now exactly the same, ConstructorCallOperatorMutator assumes mutationPoint is a "new", this one is an argument
/*
        let cast = "";

        // "new" can be part of a chained method call, go back until we get the whole chain
		let isInCall = this.mutationPoint.parent.instanceOf("call");
		let isInChainCall = false;

		// Get type of mutation point
		if(isInCall && !isInChainCall) {
			println("Creating cast for " + this.mutationPoint.code)
			cast = this.mutationPoint.code.trim();
			
			if(cast.startsWith("new")) {
				cast = cast.substring("new".length).trim();
			}

			const indexOfPar = cast.indexOf("(");
			if(indexOfPar !== -1) {
				cast = cast.substring(0, indexOfPar);
			}

			if(cast.length !== 0) {
				cast = "(" + cast + ") ";
			}

			//println("CAST: " + cast);
		} else {

        }
  */      
		//println("CAST OUTSIDE: " + cast);
        const cast = "("+this.mutationPoint.type+")";        
		const newCode = cast + "null";

        this.previousValue = this.mutationPoint;

        this.mutationPoint = this.mutationPoint.insertReplace(newCode);


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
        return `Invalid Key Intent Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
    }

    toJson() {
        return {
            mutationOperatorArgumentsList: [],
            operator: this.name,
            isAndroidSpecific: this.isAndroidSpecific(),
        };
    }
}
