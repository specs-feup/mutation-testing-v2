laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");
laraImport("weaver.WeaverJps");
laraImport("weaver.Query");

class RemoveNullCheck extends Mutator {
	constructor() {
		//Parent constructor
		super("RemoveNullCheck")

		this.newValue = undefined;
		this.mutationPoints = [];

		this.currentIndex = 0;
		this.previousValue = undefined;
	}


	addJp($joinpoint) {
		if ($joinpoint.instanceOf("binaryExpression") && ($joinpoint.operator === '!=' || $joinpoint.operator === '==')) {
			if ($joinpoint.rhs.type === "<nulltype>" || $joinpoint.lhs.type === "<nulltype>") {
				this.mutationPoints.push($joinpoint);
				return true;
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
			return this.newValue;
		} else {
			if (this.currentIndex < this.mutationPoints.length) {
				return this.mutationPoints[this.currentIndex];
			} else {
				return undefined;
			}
		}
	}

	_mutatePrivate() {

		var mutationPoint = this.mutationPoints[this.currentIndex];

		this.previousValue = mutationPoint.copy();

		if (mutationPoint.operator === '!=') {
			mutationPoint.setOperator('==');
			this.newValue = mutationPoint;
		}

		else if (mutationPoint.operator === '==') {
			mutationPoint.setOperator('!=');
			this.newValue = mutationPoint;
		}

		this.currentIndex++;

		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": " + this.previousValue
			+ " to " + this.newValue);
		println("/*--------------------------------------*/");

	}


	_restorePrivate() {
		// Restore operator
		println("Restore  prev: " + this.previousValue.code);
		println("Restore new: " + this.newValue.code);
		this.newValue.insertReplace(this.previousValue);
		this.newValue = undefined;
		this.previousValue = undefined;
	}

}