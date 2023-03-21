laraImport("lara.mutation.Mutator");

/**
 *  @param {$joinPoint} $joinPoint - A join point to use as startpoint to search for constructor calls to replace with null.
 */
class ConstructorCallMutator extends Mutator {
	//Parent constructor
	constructor() {
		super("ConstructorCallMutator");
		
		this.extraArgs = arrayFromArgs(arguments, 1);

		this.toMutate = [];
		this.totalMutations = -1;
		this.currentIndex = 0;

		this.$referenceParent = undefined;
		this.$originalParent = undefined;


		// Checks
		if (this.extraArgs.length != 0)
			throw new Error("Expected only 1 argument but received " + (this.extraArgs.length + 1));

	}



	addJp($joinpoint) {
		if ($joinpoint.instanceOf("reference") && $joinpoint.name === "<init>" && $joinpoint.type === "Executable" && $joinpoint.parent.srcCode !== "super()") {
			this.toMutate.push($joinpoint);
			this.totalMutations = this.toMutate.length;
			return true;
		}

		return false;
	}


	hasMutations() {
		return this.currentIndex < this.totalMutations;
	}


	_mutatePrivate() {
		this.$referenceParent = this.toMutate[this.currentIndex++].parent;

		this.$originalParent = this.$referenceParent.copy();
		this.$referenceParent = this.$referenceParent.insertReplace("null");

		println("/*--------------------------------------*/");
		println("Mutating operator n." + this.currentIndex + ": " + this.$originalParent
			+ " to " + this.$referenceParent);
		println("/*--------------------------------------*/");

	}

	_restorePrivate() {
		this.$referenceParent = this.$referenceParent.insertReplace(this.$originalParent);
		this.$originalParent = undefined;
		this.$referenceParent = undefined;
	}

	getMutationPoint() {
		if (this.isMutated) {
			return this.$referenceParent;
		} else {
			if (this.currentIndex < this.toMutate.length) {
				return this.toMutate[this.currentIndex];
			} else {
				return undefined;
			}
		}
	}
}