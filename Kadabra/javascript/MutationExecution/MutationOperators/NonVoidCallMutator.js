laraImport("lara.mutation.Mutator");
laraImport("kadabra.KadabraNodes");

laraImport("weaver.WeaverJps");
laraImport("weaver.Weaver");
/**
 *  @param {$joinpoint} $joinpoint - Joinpoint used as starting point to search for non void method calls which will be replaced by a hardcoded value.
 *  Method call mutations:
 *  - If method type is primitive or boxed BOOLEAN, method call is replaced by false.
 *  - If method type is primitive INT, BYTE, SHORT OR LONG, method call is replaced by 0.
 *  - If method type is primitive FLOAT or DOUBLE, method call is replaced by 0.0.
 *  - If method type is primitive CHAR, method call is replaced by '\u0000'.
 *  - If method type is non of the above, method call is replaced by null.
 */
class NonVoidCallMutator extends Mutator {
	constructor() {
		//Parent constructor
		super("NonVoidCallMutator");

		// Instance variables
		this.toMutate = [];
		this.currentIndex = 0;

		this.$originalCallNode = undefined;
		this.$callNode = undefined;

	}


	/*** IMPLEMENTATION OF INSTANCE METHODS ***/

	// Analyze method calls available for Non Void Call mutation and store them
	addJp($joinpoint) {

		// Map method call type to the respective mutation value (if a type is not on the list, it's mapped to 'null' value)
		let typeToValue = {
			'boolean': 'false',
			'Boolean': 'false',
			'int': '0',
			'byte': '0',
			'short': '0',
			'long': '0',
			'float': '0.0',
			'double': '0.0',
			'char': '\u0000',
			'String': '\u0000'
		};
		let typeList = Object.keys(typeToValue);

		let hasMutations = false;

		if ($joinpoint.instanceOf('assignment') || $joinpoint.instanceOf('localVariable') || $joinpoint.instanceOf('if') || $joinpoint.instanceOf('loop')) {


			let descendants = ($joinpoint.instanceOf('if') || $joinpoint.instanceOf('loop')) ? $joinpoint.cond.descendants : $joinpoint.descendants;

			for (let descendant of $joinpoint.descendants) {
				println("dentro1   " + descendant)
				try {
					if (descendant.instanceOf('call') && descendant.returnType !== 'void') {
						// Store call for later modification
						println("dentro2   " + descendant);
						let mutationValue = typeList.includes(descendant.returnType) ? typeToValue[descendant.returnType] : 'null';
						println("mutationValue  " + mutationValue);
						this.toMutate.push([descendant, mutationValue]);
						hasMutations = true;
					}
				} catch (e) {
					println("ERROR: " + e + "\n" + descendant.attributes);
				}
			}
		}
		return hasMutations;
	}

	hasMutations() {
		return this.currentIndex < this.toMutate.length;
	}


	_mutatePrivate() {
		let mutationInfo = this.toMutate[this.currentIndex];

		if (!mutationInfo == "undefined") {
			this.$callNode = mutationInfo[0];
			let mutationValue = mutationInfo[1];

			this.$originalCode = this.$callNode.parent.srcCode;

			/* Char and String mutation value is a null character, which generates a null node when using it directly with insertReplace.
			   A KadabraNodes.literal is required to solve the problem */
			if (this.$callNode.returnType === 'char' || this.$callNode.returnType === 'String') {
				try {
					let mutatedNode = KadabraNodes.literal(mutationValue, this.$callNode.returnType);
				} catch (e) { println("ERROR: " + e); }
				println("Nope!!!!");
				this.$callNode.insertReplace(mutatedNode);
			} else {
				println("Perhaps!!!!");
				this.$callNode.insertReplace(mutationValue);
			}

			this.isMutated = true;

			println("/*--------------------------------------*/");
			println("Mutating operator n." + this.currentIndex + ": " + this.$originalCallNode
				+ " to " + this.$callNode);
			println("/*--------------------------------------*/");
		}
	}
	_restorePrivate() {

		//println("$callNode ->  " + this.$callNode);
		//println("originalCallNode ->  " + this.$originalCode);
		//println("Original parent -> " + this.$callNode.parent.srcCode);

		try {
			this.getMutationPoint().parent.insertReplace(this.$originalCode);
		} catch (e) {
			println("ERROR!!! " + e);
			//notImplemented("potato");
		}

		println("Parent restored -> " + this.getMutationPoint().parent.srcCode + "\n\n\n\n");

		this.currentIndex++;
		this.isMutated = false;
		this.$originalCallNode = undefined;
		this.$callNode = undefined;
	}


	getMutationPoint() {
		if (this.currentIndex < this.toMutate.length) {
			return this.toMutate[this.currentIndex][0];
		} else {
			return undefined;
		}
	}

	toJson() {
		return {
			mutationOperatorArgumentsList: [],
			operator: this.name,
		};
	}
}

