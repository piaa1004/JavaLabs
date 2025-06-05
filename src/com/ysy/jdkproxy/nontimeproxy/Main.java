package com.ysy.jdkproxy.nontimeproxy;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.ysy.jdkproxy.nontimeproxy.service.*;

public class Main {
	
	public static void main(String[] args) {
        printTitle("🚀 Non Dynamic Proxy Demo");

        // List.of 메서드를 통해서
        // ServiceRunner 클래스의 인스턴스를 엘리먼트로 가지는 리스트를 만듦.
        List<ServiceRunner> services = List.of(
            new ServiceRunner("👋 인사", () -> {
                GreetingService s = new GreetingServiceImpl();
                s.sayHello("John");
                //////////////////////////////////////////
                // Cross-Cutting Concern : 공통 관심사!
                s.logToFile();
                //////////////////////////////////////////
            }),

            new ServiceRunner("🧮 덧셈", () -> {
                MathService s = new MathServiceImpl();
                int result = s.add(10, 20);
                System.out.println("결과: " + result);
                s.logToFile();
            }),

            new ServiceRunner("🌦️ 날씨", () -> {
                WeatherService s = new WeatherServiceImpl();
                System.out.println("결과: " + s.getWeather("서울"));
                s.logToFile();
            }),

            new ServiceRunner("📰 뉴스", () -> {
                NewsService s = new NewsServiceImpl();
                System.out.println("결과: " + s.getHeadline());
                s.logToFile();
            }),

            new ServiceRunner("✖️ 곱셈", () -> {
                CalculatorService s = new CalculatorServiceImpl();
                System.out.println("결과: " + s.multiply(3.5, 4.2));
                s.logToFile();
            })
        );

        /*
         default void forEach(Consumer<? super ServiceRunner> action) {
         	Objects.requireNonNull(action);
        	for (ServiceRunner t : services) {
            	action.accept(t);
        	}
    	}
         */
        services.forEach(ServiceRunner::run);

        printFooter("✅ 모든 서비스 실행 완료");
    }

    

    // 출력 꾸밈
    private static void printTitle(String title) {
    	// =========================================================
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        " + title);
        System.out.println("=".repeat(60) + "\n");
    }
    // =====================================================================

    private static void printFooter(String message) {
    	// ---------------------------------------------------------------
        System.out.println("\n" + "-".repeat(60));
        System.out.println("        " + message);
        System.out.println("-".repeat(60));
    }

    // 래퍼 클래스
    static class ServiceRunner {
        private final String title;
        private final Runnable action;

        // Runnable 인터페이스에서 정의한 run() 메서드!!
        public ServiceRunner(String title, Runnable action) {
            this.title = title;
            this.action = action;
        }

        public void run() {
            System.out.printf("▶ %s 서비스 실행 중...\n", title);
            try {
                action.run();
            } catch (Exception e) {
                //System.out.println("❌ 오류 발생: " + e.getMessage());
            }
            System.out.println();
        }
    }

}
