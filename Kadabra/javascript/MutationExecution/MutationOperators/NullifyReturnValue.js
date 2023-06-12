laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Query");

class NullifyReturnValue extends Mutator {
	constructor() {
		//Parent constructor
		super("NullifyReturnValue");

		this.mutationPoint = undefined;
		this.mutationPoints = [];

		this.currentIndex = 0;
		this.previousValue = undefined;

	}

	isAndroidSpecific() {
		return false;
	}
	addJp(joinpoint) {
		if (joinpoint.instanceOf('return')) {
			if (!joinpoint.ancestor('method').returnRef.isPrimitive) {
				this.mutationPoints.push(joinpoint);

			}
		}
		if (this.mutationPoints.length > 0) {
			return true;
		}

		return false;
	}


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
		this.previousValue = this.mutationPoint.code;
		println("Prev: " + this.mutationPoint);
		this.mutationPoint = this.mutationPoint.insertReplace("return null");

		this.currentIndex++;

		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
			+ " to " + this.mutationPoint);
		println("/*--------------------------------------*/");

	}
	_restorePrivate() {
		// Restore operator
		println("Restore  prev: " + this.previousValue);
		println("Restore new: \n" + this.mutationPoint);
		this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue + ";");
		this.mutationPoint = undefined;
	}

	toString() {
		return `Nullify Return Value  Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
	}

	toJson() {
		return {
			mutationOperatorArgumentsList: [],
			operator: this.name,
			isAndroidSpecific: this.isAndroidSpecific(),
		};
	}
}