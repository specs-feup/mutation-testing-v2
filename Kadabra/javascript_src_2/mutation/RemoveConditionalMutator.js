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
		this.toMutate = [];
		this.currentIndex = 0;

		this.originalConditionalClause = undefined;
		this.$conditionalClause = undefined;

	}

	/*** IMPLEMENTATION OF INSTANCE METHODS ***/


	addJp($joinpoint) {

		// A conditional can be either an if or a ternary operator
		if ($joinpoint.instanceOf('if') || $joinpoint.instanceOf('ternary') || $joinpoint.instanceOf('loop')) {
			this.toMutate.push($joinpoint.cond);
			return true;
		}
		return false;
	}

	hasMutations() {
		return this.currentIndex < this.toMutate.length;
	}


	_mutatePrivate() {
		this.$conditionalClause = this.toMutate[this.currentIndex++];
		this.originalConditionalClause = this.$conditionalClause.copy();

		let mutatedCondition = 'true';

		this.$conditionalClause = this.$conditionalClause.insertReplace(mutatedCondition);

		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": " + this.originalConditionalClause
			+ " to " + this.$conditionalClause);
		println("/*--------------------------------------*/");

	}

	_restorePrivate() {
		this.$conditionalClause = this.$conditionalClause.insertReplace(this.originalConditionalClause);

		this.originalConditionalClause = undefined;
		this.$conditionalClause = undefined;
	}

	getMutationPoint() {
		if (this.isMutated && this.$conditionalClause !== null) {
			return this.$conditionalClause;
		} else {
			if (this.currentIndex < this.toMutate.length) {
				return this.toMutate[this.currentIndex];
			} else {
				return undefined;
			}
		}
	}
}