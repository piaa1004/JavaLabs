package com.ysy.jdkproxy.util;

import java.lang.reflect.*;
import java.security.*;
import java.util.Enumeration;

public final class ProxyInspector {

    private ProxyInspector() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    public static void inspect(Object proxy) {
        if (proxy == null) {
            System.out.println("⚠️ proxy 객체가 null입니다.");
            return;
        }

        Class<?> clazz = proxy.getClass();

        System.out.println("📌 [프록시 클래스 이름]");
        System.out.println("    → " + clazz.getName());

        // 프록시 클래스 여부 확인
        boolean isProxyClass = Proxy.isProxyClass(clazz);
        System.out.println("🧠 [Proxy.isProxyClass 여부]");
        System.out.println("    → " + (isProxyClass ? "✅ 다이나믹 프록시 클래스입니다." : "❌ 일반 클래스입니다."));

        // InvocationHandler 정보 출력
        if (isProxyClass) {
            try {
                InvocationHandler handler = Proxy.getInvocationHandler(proxy);
                System.out.println("\n🎯 [InvocationHandler 정보]");
                System.out.println("    → 클래스: " + handler.getClass().getName());
                System.out.println("    → toString(): " + handler);
            } catch (IllegalArgumentException e) {
                System.out.println("    → ❌ InvocationHandler를 가져올 수 없습니다: " + e.getMessage());
            }
        }

        // final, abstract 여부
        int modifiers = clazz.getModifiers();
        System.out.println("\n🔐 [클래스 final / abstract 확인]");
        System.out.println("    → final 여부: " + Modifier.isFinal(modifiers));
        System.out.println("    → abstract 여부: " + Modifier.isAbstract(modifiers));

        // ProtectionDomain 비교
        ProtectionDomain proxyPD = clazz.getProtectionDomain();
        ProtectionDomain objectPD = Object.class.getProtectionDomain();
        boolean sameDomain = proxyPD.equals(objectPD);
        System.out.println("\n🛡️ [ProtectionDomain 비교]");
        System.out.println("    → proxy 클래스와 java.lang.Object의 ProtectionDomain 동일 여부: " + sameDomain);
        System.out.println("    → 코드 소스: " + proxyPD.getCodeSource());

        // 권한 목록 확인
        PermissionCollection permissions = proxyPD.getPermissions();
        boolean hasAllPermission = false;
        if (permissions != null) {
            permissions.implies(new AllPermission()); // 강제 초기화
            for (Enumeration<Permission> e = permissions.elements(); e.hasMoreElements(); ) {
                Permission perm = e.nextElement();
                if (perm instanceof AllPermission) {
                    hasAllPermission = true;
                    break;
                }
            }
        }
        System.out.println("\n🔒 [AllPermission 확인]");
        System.out.println("    → java.security.AllPermission 부여 여부: " + (hasAllPermission ? "✅ 있음" : "❌ 없음"));

        // 슈퍼클래스
        System.out.println("\n🔹 [슈퍼클래스]");
        System.out.println("    → " + clazz.getSuperclass().getName());

        // 인터페이스
        System.out.println("\n🔹 [구현된 인터페이스]");
        for (Class<?> iface : clazz.getInterfaces()) {
            System.out.println("    → " + iface.getName());
        }

        // 메서드
        System.out.println("\n🔹 [선언된 메서드]");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("    ▶ " + method.getName());
            System.out.println("       - 리턴 타입: " + method.getReturnType().getName());
            System.out.println("       - 파라미터 수: " + method.getParameterCount());
            for (Class<?> paramType : method.getParameterTypes()) {
                System.out.println("         * 파라미터 타입: " + paramType.getName());
            }
        }

        // 생성자
        System.out.println("\n🔹 [생성자]");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            System.out.println("    → " + constructor);
        }

        System.out.println("\n✅ 프록시 클래스 정보 출력 완료.");
    }
}
