package weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//월별 30일간 날씨 데이터를 생성하고 조회하는 클래스
public class WeatherCalc {
	// 내부에서만 사용하기 위해 private 선언하기
    private List<Weather> weatherList = new ArrayList<>(); // 30일간의 날씨 정보를 저장할 리스트 (동적 배열)
    private Random random = new Random(); // 랜덤으로 날씨 생성
    private Scanner scanner = new Scanner(System.in); // 사용자의 입력값을 받을 스캐너
    private int inputMonth; // 2번 문제 구현 위한 새 변수 선언
    
    // Main 클래스에서 실행할 메소드
    public void start() {
    	while (true) {
    		inputMonth(); // 사용자로부터 월 입력받기
    		
            if (inputMonth == 0) {
                System.out.println("날씨 조회를 종료합니다.");
                break; // 0 입력시 종료
            }
	    	generateWeather(inputMonth); // 해당 월의 날씨 랜덤 생성
	        printWeather(); // 사용자의 입력값을 날짜로 받아 해당 날의 날씨 출력
    	}
    }
    
    // 사용자로부터 월을 입력받는 메서드
    private int inputMonth() {
        while (true) {
            System.out.print("날씨 데이터를 확인할 월을 입력하세요 (1~12, 0 입력시 종료): ");
            inputMonth = scanner.nextInt();
            
            if (inputMonth == 0) {
            	return 0; // 종료 조건
            } else if (inputMonth >= 1 && inputMonth <= 12) {
            	return inputMonth;
            } else { // 예외 처리
            	System.out.println("1~12 사이의 숫자를 입력해주세요.");
            }
        }
    }
    
    // 랜덤으로 30일간의 날씨 데이터 생성하고 리스트에 저장하는 메소드
    private void generateWeather(int inputMonth) {
    	weatherList.clear(); // 이전 월 데이터 초기화, 데이터 누적 방지
        int baseTemp; // 기준 온도

        if (inputMonth == 12 || inputMonth == 1 || inputMonth == 2) {
            baseTemp = 0;  // 겨울
        } else if (inputMonth >= 3 && inputMonth <= 5) {
            baseTemp = 10; // 봄
        } else if (inputMonth >= 6 && inputMonth <= 8) {
            baseTemp = 20; // 여름
        } else {
            baseTemp = 10; // 가을
        }
        
        for (int i = 1; i <= 30; i++) {
            int temperature = baseTemp - 10 + random.nextInt(21); // 기준 온도 ±10
            int ratio = random.nextInt(100); // 0~99

            String condition;
            if (ratio < 50) { 
                condition = "맑음"; // 50% 확률
            } else if (ratio < 80) { 
                condition = "흐림"; // 30% 확률
            } else {
                condition = "비"; // 20% 확률
            }
            // 리스트에 정보 저장
            weatherList.add(new Weather(i, temperature, condition));
        }
    }
    
    // 사용자의 입력을 받아 해당 날짜의 날씨를 출력하는 메소드
    private void printWeather() {
        while (true) {
            System.out.print("확인할 날짜를 입력하세요 (1~30, 0 입력 시 뒤로가기): ");
            int inputDate = scanner.nextInt();

            if (inputDate == 0) {
            	break; // 0 입력시 월 선택으로 돌아가기
            }

            if (inputDate >= 1 && inputDate <= 30) {
            	// Weather 클래스의 객체 반환, 오버라이드 되어있는 .toString() 메소드 호출
                System.out.println(weatherList.get(inputDate - 1)); // 리스트 인덱스는 0부터 시작하므로 -1
            } else {
                System.out.println("1~30 사이의 날짜를 입력하세요."); // 예외처리
            }
        }
    }
}
