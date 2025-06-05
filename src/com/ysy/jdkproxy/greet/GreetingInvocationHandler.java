package com.ysy.jdkproxy.greet;

import java.lang.reflect.*;

public class GreetingInvocationHandler implements InvocationHandler {
	
    @Override
    // Object proxy : 런타임시 생성된 Proxy 클래스를 상속한 $Proxy의 인스턴스
    // Method method : proxy가 구현한 인터페이스의 특정 메서드에 대한 정보가 Method method임.
    // Object[] args : 이 method를 호출할 때 전달할 아규먼트들임.
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	// 개발자 입장에서는 이 세 개의 아규먼트를 가지고 특정 인터페이스의 디폴트 메서드를 호출하기 어렵다.
        
    	if (method.isDefault()) {
            // Java 15+에서 디폴트 메서드를 호출할 수 있는 정식 API
            return InvocationHandler.invokeDefault(proxy, method, args);
        }

        // 사용자 정의 로직
        System.out.println("👋 Custom logic for: " + method.getName());
        return null;
    }
}