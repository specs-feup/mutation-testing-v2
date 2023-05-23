package org.feup.Mutation_Testing_Backend_Final.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperatorValidator {
    private HashMap<String, List<String>> validArguments;
    private HashMap<String, Integer> numberArguments;

    public OperatorValidator() {
        validArguments =  new HashMap<>();
        numberArguments =  new HashMap<>();

        List<String> BinaryMutator = new ArrayList<>();
        BinaryMutator.add("+");
        BinaryMutator.add("-");
        BinaryMutator.add("/");
        BinaryMutator.add("%");
        BinaryMutator.add("*");
        validArguments.put("BinaryMutator", BinaryMutator);
        numberArguments.put("BinaryMutator", 2);

        List<String> UnaryAddOperatorMutator = new ArrayList<>();
        UnaryAddOperatorMutator.add("++");
        validArguments.put("UnaryAddOperatorMutator", UnaryAddOperatorMutator);
        numberArguments.put("UnaryAddOperatorMutator", 1);

        List<String> AssignmentOperatorMutator = new ArrayList<>();
        AssignmentOperatorMutator.add("+=");
        AssignmentOperatorMutator.add("&=");
        AssignmentOperatorMutator.add("-=");
        AssignmentOperatorMutator.add("/=");
        validArguments.put("AssignmentOperatorMutator", AssignmentOperatorMutator);
        numberArguments.put("AssignmentOperatorMutator", 2);

        List<String> ConstructorCallMutator = new ArrayList<>();
        validArguments.put("ConstructorCallMutator", ConstructorCallMutator);
        numberArguments.put("ConstructorCallMutator", 0);
    }

    public boolean validate(List<String> operatorNameList, List<List<String>> operatorArgumentList) {
        /*for (int i = 0; i < operatorNameList.size(); i++){
            //unknown operator
            if (validArguments.get(operatorNameList.get(i)) == null){
                return false;
            }

            //invalid number of operators
            if (operatorArgumentList.get(i).size()!=numberArguments.get(operatorNameList.get(i))){
                return false;
            }

            for(String parameter: operatorArgumentList.get(i)){
                //unknown parameter
                if (!validArguments.get(operatorNameList.get(i)).contains(parameter)){
                    return false;
                }
            }

        }*/
        return true;
    }
}
