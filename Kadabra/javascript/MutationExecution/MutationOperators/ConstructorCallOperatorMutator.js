laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");
/**
 *  @param {$joinPoint} $joinPoint - A join point to use as startpoint to search for constructor calls to replace with null.
 */
class ConstructorCallOperatorMutator extends Mutator {
	//Parent constructor
	constructor() {
		super("ConstructorCallOperatorMutator");
		this.mutationPoints = [];
		this.currentIndex = 0;
		this.mutationPoint = undefined;
		this.previousValue = undefined;

	};

	isAndroidSpecific() {
		return false;
	}


	addJp(joinpoint) {

		if (joinpoint != undefined && joinpoint.instanceOf("new") ) {

			if (joinpoint.children[0] != undefined && joinpoint.children[0].instanceOf("reference") && joinpoint.children[0].name === "<init>" && joinpoint.children[0].type === "Executable") {

				this.mutationPoints.push(joinpoint);
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


	_mutatePrivate() {
		this.mutationPoint = this.mutationPoints[this.currentIndex++];

		// "new" can be part of a chained method call, go back until we get the whole chain
		//println("MUTATION POINT PARENT: " + this.mutationPoint.parent)
		let inInCall = this.mutationPoint.parent.instanceOf("call");
		let isInChainCall = false;
		while(this.mutationPoint.parent !== undefined && this.mutationPoint.parent.instanceOf("call") && this.mutationPoint.parent.children[0].same(this.mutationPoint)) {
			this.mutationPoint = this.mutationPoint.parent;
			isInChainCall = true;
			//println("CHANGING MUTATION POINT PARENT TO: " + this.mutationPoint.parent)
		}

		// Get type of mutation point
		let cast = "";
		if(inInCall && !isInChainCall) {
			let cast = this.mutationPoint.code.trim();
			
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
		}


		this.previousValue = this.mutationPoint;
		this.mutationPoint = this.mutationPoint.insertReplace(cast + "null");


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
	toString() {
		return `Constructor Call Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
	}

	toJson() {
		return {
			mutationOperatorArgumentsList: [],
			operator: this.name,
			isAndroidSpecific: this.isAndroidSpecific(),
		};
	}

}
