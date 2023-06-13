laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.Query");
laraImport("weaver.Weaver");
/**
 *  @param {$joinpoint} $joinpoint - Joinpoint used as starting point to search for if clauses whose condition will be replaced by true.
 */

class RemoveConditionalMutator extends Mutator {
	constructor() {
		//Parent constructor
		super("RemoveConditionalMutator");
		// Instance variables
		this.mutationPoints = [];
		this.currentIndex = 0;

		this.previousValue = undefined;
		this.mutationPoint = undefined;

	}

	isAndroidSpecific() {
		return false;
	}
	/*** IMPLEMENTATION OF INSTANCE METHODS ***/


	addJp(joinpoint) {

		// A conditional can be either an if or a ternary operator
		if ((joinpoint.instanceOf('if') || joinpoint.instanceOf('ternary') || joinpoint.instanceOf('loop')) && joinpoint.cond.toString() != "true") {
			println("Adding joinpoint " + joinpoint.children[0])
			this.mutationPoints.push(joinpoint.cond);

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
		if (this.isMutated && this.mutationPoint !== null) {
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
		this.mutationPoint = this.mutationPoints[this.currentIndex++];
		println("cc" + this.mutationPoint);
		this.previousValue = this.mutationPoint;

		this.mutationPoint = this.mutationPoint.insertReplace("true");

		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
			+ " to " + this.mutationPoint);
		println("/*--------------------------------------*/");

	}

	_restorePrivate() {
		this.mutationPoint = this.mutationPoint.insertReplace(this.previousValue);

		this.previousValue = undefined;
	}


	toString() {
		return `Remove Conditional Mutator from ${this.previousValue} to ${this.mutationPoint}, current mutation points ${this.mutationPoints}, current mutation point ${this.mutationPoint} and previous value ${this.previousValue}`;
	}

	toJson() {
		return {
			mutationOperatorArgumentsList: [],
			operator: this.name,
			isAndroidSpecific: this.isAndroidSpecific(),
		};
	}
}