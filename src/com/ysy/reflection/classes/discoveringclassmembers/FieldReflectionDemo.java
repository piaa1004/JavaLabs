package com.ysy.reflection.classes.discoveringclassmembers;

import java.lang.reflect.Field;

class Parent1 {
    public String publicParentField = "parent";
    private String privateParentField = "hidden";
}

class Child1 extends Parent1 {
    public int publicChildField = 123;
    private int privateChildField = 456;
}

public class FieldReflectionDemo {
    public static void main(String[] args) {
        Class<?> clazz = Child1.class;

        System.out.println("🔍 1. getField(\"publicChildField\")");
        try {
            Field field = clazz.getField("publicChildField");
            System.out.println("✔ Found: " + field.getName());
        } catch (NoSuchFieldException e) {
            System.out.println("⚠ Not found: " + e.getMessage());
        }

        System.out.println("🔍 2. getDeclaredField(\"privateChildField\")");
        try {
            Field field = clazz.getDeclaredField("privateChildField");
            System.out.println("✔ Found: " + field.getName());
        } catch (NoSuchFieldException e) {
            System.out.println("⚠ Not found: " + e.getMessage());
        }

        System.out.println("\n📜 3. getFields() → public + 상속 포함");
        Field[] publicFields = clazz.getFields();
        for (Field f : publicFields) {
            System.out.println("✔ Public field: " + f.getName());
        }

        System.out.println("\n📜 4. getDeclaredFields() → 현재 클래스 내 모든 필드");
        // excludes inherited fields!!!
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println("✔ Declared field: " + f.getName());
        }
    }
}