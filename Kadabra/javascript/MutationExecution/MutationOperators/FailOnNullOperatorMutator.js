laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");


class FailOnNullOperatorMutator extends Mutator {
	constructor() {

		//Parent constructor
		super("FailOnNullOperatorMutator")
		this.mutationPoints = [];
		this.currentIndex = 0;
		this.mutationPoint = undefined;
		this.previousValue = undefined;
	}

	isAndroidSpecific() {
		return false;
	}
	addJp(joinpoint) {

		if (joinpoint != undefined && joinpoint.instanceOf('loop')) {

			if (joinpoint.children[0] != undefined && joinpoint.children[1] != undefined && joinpoint.children[0].instanceOf('localVariable') && joinpoint.children[1].instanceOf('var')) {
				this.mutationPoints.push(joinpoint.children[1]);
			}
		} else {
			if (joinpoint.instanceOf('assignment') && joinpoint != undefined && joinpoint.children[0] != undefined && joinpoint.children[0].children[0] != undefined && joinpoint.children[0].children[0].type == 'LocalVariable') {

				if (joinpoint.instanceOf('assignment') && joinpoint.children[0].children[0].type == 'LocalVariable' && joinpoint.children[1].children[0] != undefined && joinpoint.children[1] != undefined && joinpoint.children[1].children[0].type == 'LocalVariable') {
					this.mutationPoints.push(joinpoint.children[1]);
				}
			}

		}
		if (this.mutationPoints.length > 0) {
			return true;
		} return false;

	};



	/*** IMPLEMENTATION OF INSTANCE METHODS ***/
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

		let codeSnippet = "if(" + this.mutationPoint + "== null) { throw new java.lang.NullPointerException(\"Fail on null\"); }";

		this.mutationPoint = this.mutationPoint.insertBefore(codeSnippet);



		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": " + this.previousValue +
			" to " + this.mutationPoint);
		println("/*--------------------------------------*/");
		this.currentIndex++;
	}

	_restorePrivate() {
		// Restore operator
		this.mutationPoint = this.mutationPoint.replaceWith("");

	}


	toString() {
		return `Fail On Null Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
	}

	toJson() {
		return {
			mutationOperatorArgumentsList: [],
			operator: this.name,
			isAndroidSpecific: this.isAndroidSpecific(),
		};
	}
}
