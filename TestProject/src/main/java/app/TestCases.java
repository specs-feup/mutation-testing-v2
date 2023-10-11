package app;

import java.util.*;

public class TestCases {

	public static void main(String[] args) {
		System.out.println("Add 23 with 2: " + TestCases.arithmeticOperators01(2));
	}

    public static int arithmeticOperators01(int value) {
    	List<Integer> b;
        int a;
        a = 23 + value;
        a = 23 - value;
        a = 23 * value;
        a = 23 / value;
        a = 23 % value;
        return a;
    }

    public static int arithmeticOperators02(int value) {
        int a;
        a = 23 + value - 3;
        a = 23 - value * 3;
        a = 23 * value / 3;
        a = 23 / value % 3;
        a = 23 % value + 3;
        return a;
    }

    public static int bitwiseOperators(int value) {
        List<Integer> b;
        int a;
        a = 23 & value;
        a = 23 | value;
        a = 23 ^ value;
        return a;
    }

    public static int shiftOperators(int value) {
        List<Integer> b;
        int a;
        a = 23 >> value;
        a = 23 << value;
        a = 23 >>> value;
        return a;
    }

    //Check for mutant syntax errors
    public static int comparisonOperators(int value) {
        int a = 30;

        if ((value == a) || (value != 20)) {
            a = value;
        }
        
        return a;
    }

    public static int comparisonOperators2(int value) {
        int a = 30;

        if (a > value) {
            a = value + 10;
        }

        if (a < value) {
            a = value - 20;
        }

        if (a >= value) {
            a = value + 20;
        }

        if (a <= value) {
            a = value - 30;
        }

        
        return a;
    }

    public static int loginalOperators(int value) {
        int a = 30;

        if ((a > value) && (a < value)) {
            a = value - 20;
        }

        if ((a >= value) || (a <= value)) {
            a = value - 30;
        }

        
        return a;
    }    

    public static int assignmentOperators(int value) {
        int a = value;

        a += 20 + value;
        a -= 30;
        a *= 40;
        a /= 50;
        a %= 60;
        
        return a;
    }

    public int ternary() {

        int a = 10;
        int b = 15;

        b = (a > b)? (a*b): 0;

        return b;
    }

    public int unaryTest2() {

        int a = 10;
        int b = 15;
        if (!(a < b)) {
            return a;
        }
        return b;
    }

    public static TestCases constructorCall() {
        Object a = new Object();
        
        return new TestCases();
    }

    public static List failOnNull() {
        List<String> res = new LinkedList<>();
        Iterator<String> members = res.iterator();
        for (String member : res){
            System.out.println(member);
        }
        return res;
    }
}

