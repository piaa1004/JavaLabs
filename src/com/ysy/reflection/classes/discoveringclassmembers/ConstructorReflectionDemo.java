package com.ysy.reflection.classes.discoveringclassmembers;

import java.lang.reflect.Constructor;

class Example {
    public Example() {
        System.out.println("👋 public no-arg constructor called");
    }

    public Example(String name) {
        System.out.println("📣 public constructor with String: " + name);
    }

    private Example(int id) {
        System.out.println("🔒 private constructor with int: " + id);
    }
}

public class ConstructorReflectionDemo {
    public static void main(String[] args) {
        Class<?> clazz = Example.class;

        try {
            // 1. public 생성자(String) 사용
            Constructor<?> publicCtor = clazz.getConstructor(String.class);
            Object publicInstance = publicCtor.newInstance("ChatGPT");
            System.out.println("✔ publicInstance created: " + publicInstance.getClass().getSimpleName());

            // 2. private 생성자(int) 사용
            Constructor<?> privateCtor = clazz.getDeclaredConstructor(int.class);
            privateCtor.setAccessible(true);  // 접근 가능하게 설정
            Object privateInstance = privateCtor.newInstance(42);
            System.out.println("✔ privateInstance created: " + privateInstance.getClass().getSimpleName());

            // 3. public 기본 생성자() 사용
            Constructor<?> noArgCtor = clazz.getConstructor();
            Object defaultInstance = noArgCtor.newInstance();
            System.out.println("✔ defaultInstance created: " + defaultInstance.getClass().getSimpleName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}