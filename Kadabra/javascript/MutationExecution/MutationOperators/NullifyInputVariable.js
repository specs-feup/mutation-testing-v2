laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Query");

class NullifyInputVariable extends Mutator {
	constructor() {
		//Parent constructor
		super("NullifyInputVariable");

		this.newValue = undefined;
		this.mutationPoints = [];
		this.currentIndex = 0;
		this.previousValue = undefined;
		this.mutationPoint = undefined;

	}



	addJp(joinpoint) {
		if (joinpoint != undefined && joinpoint.instanceOf('method')) {
			for (var param of joinpoint.params) {
				if (!param.isPrimitive) {
					this.mutationPoints.push(joinpoint);
					return true;
				}
			}
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


		this.previousValue = this.mutationPoint;

		for (var param of this.mutationPoint.params) {
			if (!param.isPrimitive && this.mutationPoint.body != undefined) {
				this.mutationPoint.body.insertBegin(" { [[param.name]] = null } ");
			}
		}
		this.currentIndex++;
		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": "
			+ " to " + this.mutationPoint);
		println("/*--------------------------------------*/");
	}


	_restorePrivate() {
		this.mutationPoint.insertReplace(this.previousValue);
		this.mutationPoint = undefined;

	}

	toString() {
		return `Nullify input Value Intent Operator Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
	}

	toJson() {
		return {
			mutationOperatorArgumentsList: [],
			operator: this.name,
			isAndroidSpecific: this.isAndroidSpecific(),
		};
	}
}
