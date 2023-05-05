laraImport("kadabra.KadabraNodes");
laraImport("mutation.*");


var Mutators = [
	new BinaryMutator("+", "-"),
	new BinaryMutator("+", "/"),
	new BinaryMutator("+", "%"),
	new BinaryMutator("-", "+"),
	new BinaryMutator("-", "/"),
	new BinaryMutator("-", "*"),
	new BinaryMutator("-", "%"),
	new UnaryAddOperatorMutator("++"),
	new UnaryAddOperatorMutator("--"),
	new AssignmentOperatorMutator("+=", "+="),
	new AssignmentOperatorMutator("+=", "-="),
	new AssignmentOperatorMutator("+=", "%="),
	new AssignmentOperatorMutator("+=", "/="),
	new AssignmentOperatorMutator("+=", "*="),
	new AssignmentOperatorMutator("-=", "+="),
	new AssignmentOperatorMutator("-=", "-="),
	new AssignmentOperatorMutator("-=", "%="),
	new AssignmentOperatorMutator("-=", "/="),
	new AssignmentOperatorMutator("-=", "*="),
	//new ConstructorCallMutator(),
	//new NullIntentOperatorMutator(),
	//new InvalidKeyIntentPutExtraOperatorMutator(),
	//new NullValueIntentPutExtraOperatorMutator(),
	//new RandomActionIntentDefinitionOperatorMutator(),
	//new InvalidKeyIntentPutExtraOperatorMutator(),
	//new InvalidValueIntentPutExtraOperatorMutator(),
	//new IntentPayloadReplacementOperatorMutator(),
	//new InvalidDateOperatorMutator(),
	//new InvalidMethodCallArgumentOperatorMutator(),
	//new StringArgumentReplacementOperatorMutator(),
	//new NotSerializableOperatorMutator(),
	//new ActivityNotDefinedOperatorMutator(),
	//new ConstantMutator("1"),
	//new ConditionalAddNotOperatorMutator(),
];
