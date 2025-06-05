package com.ysy.jdkproxy.nontimeproxy.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;

public class NewsServiceImpl implements NewsService {
	private static final String LOG_FILE = "method_invocation.log";
	
    public String getHeadline() {
    	///////////////////////////////////////////////////////////////////
    	// Cross-Cutting Concern : 공통 관심사
    	ZonedDateTime now = ZonedDateTime.now();
        System.out.printf("🕒 [%s] 메서드 호출됨: %s()%n", now, "getHeadline");
        ///////////////////////////////////////////////////////////////////
        
        return "🗞️ 오늘의 헤드라인: '프록시 기반 로깅 시스템 화제'";
    }
    
    public void logToFile() {
    	ZonedDateTime time = ZonedDateTime.now();
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.printf("[%s] 호출된 메서드: %s()%n", time, "getHeadline");
        } catch (IOException e) {
            System.err.println("⚠️ 로그 파일 기록 실패: " + e.getMessage());
        }
    }

}