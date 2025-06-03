package com.ysy.jdkproxy.timeproxy;

import com.ysy.jdkproxy.timeproxy.proxy.ProxyFactory;
import com.ysy.jdkproxy.timeproxy.service.CalculatorService;
import com.ysy.jdkproxy.timeproxy.service.CalculatorServiceImpl;
import com.ysy.jdkproxy.timeproxy.service.GreetingService;
import com.ysy.jdkproxy.timeproxy.service.GreetingServiceImpl;
import com.ysy.jdkproxy.timeproxy.service.MathService;
import com.ysy.jdkproxy.timeproxy.service.MathServiceImpl;
import com.ysy.jdkproxy.timeproxy.service.NewsService;
import com.ysy.jdkproxy.timeproxy.service.NewsServiceImpl;
import com.ysy.jdkproxy.timeproxy.service.WeatherService;
import com.ysy.jdkproxy.timeproxy.service.WeatherServiceImpl;

public class Main {
	
	 public static void main(String[] args) {		 
		 
		 GreetingService greeting = ProxyFactory.createProxy(
				 new GreetingServiceImpl(), GreetingService.class);
         greeting.sayHello("John");

         MathService math = ProxyFactory.createProxy(
                 new MathServiceImpl(), MathService.class);
         System.out.println("🧮 덧셈 결과: " + math.add(5, 7));

         WeatherService weather = ProxyFactory.createProxy(
                 new WeatherServiceImpl(), WeatherService.class);
         System.out.println("🌤️ 날씨 정보: " + weather.getWeather("서울"));

         NewsService news = ProxyFactory.createProxy(
                 new NewsServiceImpl(), NewsService.class);
         System.out.println("📰 뉴스: " + news.getHeadline());

         CalculatorService calculator = ProxyFactory.createProxy(
                 new CalculatorServiceImpl(), CalculatorService.class);
         System.out.println("✖️ 곱셈 결과: " + calculator.multiply(3.5, 2));
	 }
}
