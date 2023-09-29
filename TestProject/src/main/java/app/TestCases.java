package app;

import java.util.List;

public class TestCases {

	public static void main(String[] args) {
		System.out.println("Add 23 with 2: " + TestCases.arithmeticOperatorDivision(2));
	}

    public static int arithmeticOperatorDivision(int value) {
    	List<Integer> b;
        int a;
        a = 23 + value;
        return a;
    }

    public static int arithmeticOperatorMinus(int value) {
        int a;
        a = 23 - value + 3;
        return a;
    }

    public int unaryTest2() {

        int a = 10;
        int b = 15;
        if (!(a < b)) {
            return a;
        }
        return b;

    }

}

