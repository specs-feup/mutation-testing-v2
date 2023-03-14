laraImport("kadabra.KadabraNodes");
laraImport("mutation.*");


var Mutators = [
	new BinaryMutator("+","*"),
	new BinaryMutator("+","-"),
	new BinaryMutator("+","/"),
	new BinaryMutator("+","%"),
	new BinaryMutator("-","+"),
	new BinaryMutator("-","/"),
	new BinaryMutator("-","*"),
	new BinaryMutator("-","%") 
];
