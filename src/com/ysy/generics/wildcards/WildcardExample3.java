package com.ysy.generics.wildcards;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WildcardExample3 {
	
	// Incorrect number of arguments for type List<E>; 
	// it cannot be parameterized with arguments <? extends Number, Integer>
//	public void invalidMethod(List<? extends Number super Integer> list) {
//	    // ...
//	}

	
	public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }
	
	public static void addNumbersThrough() {
		List<Integer> integerList = new ArrayList<>();
        addNumbers(integerList);
        System.out.println("Integer list: " + integerList);

        // Example with List<Number>
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println("Number list: " + numberList);

        // Example with List<Object>
        List<Object> objectList = new ArrayList<>();
        addNumbers(objectList);
        System.out.println("Object list: " + objectList);

        // Example with List<Comparable>
        // 타입 아규먼트가 raw 타입으로 정의되어 있음
        List<Comparable> comparableList = new ArrayList<>();
        addNumbers(comparableList);
        System.out.println("Comparable list: " + comparableList);

        // Example with LinkedList<Object>
        List<Object> linkedObjectList = new LinkedList<>();
        addNumbers(linkedObjectList);
        System.out.println("Linked Object list: " + linkedObjectList);
	}
	// getSuperIntegerList 메서드가 리턴하는 데이터 타입이 List<Number>
	// 그리고 getSuperIntegerList의 리턴값 데이터 타입 정의가 List<? super Integer>.
	// List<? super Integer> ret = new ArrayList<Number>()-> List<Inter>
    public static List<? super Integer> getSuperIntegerList() {
        List<Number> numList = new ArrayList<>();
        numList.add(10); // Integer는 Number의 하위 타입
        return numList;
    }

    public static void main(String[] args) {
    	
    	addNumbersThrough();
    	
    	Integer num1 = Integer.valueOf(5);
    	Integer num2 = Integer.valueOf(6);
    	num1.compareTo(num2);
    	
    	List<Integer> listI = new ArrayList<>();
    	List<Long> listL = new ArrayList<>(); 
    	List<Number> listM = new ArrayList<>(); 
    	List<Object> listO = new ArrayList<>();
    	
    	// 와일드카드로 올 수 있는 것들이
    	// Integer 와 Integer를 상속한 subtype만 올 수 있음
    	List<? extends Integer> wLists = listI;
//    	List<? extends Integer> wLists = listL;
//    	List<? extends Integer> wLists = listM;
//    	List<? extends Integer> wLists = listO;
    	
        List<? super Integer> list = getSuperIntegerList();
        list.add(20); // 추가 가능
        // Object obj = list.get(0); // 읽을 땐 Object로만 안전
        System.out.println(list); // 출력: [10, 20]
    }
}

// The type IntegerSubType cannot subclass the final class Integer
//class IntegerSubType extends Integer {
//	
//}