package midTerm;

// 추상화된 WeatherData 클래스 상속받아 특정 날짜의 날씨 정보를 담는 클래스
public class Weather extends WeatherData { 
	// Weather 객체 생성자, 상위 클래스 생성자 호출
    public Weather(int date, int temperature, String condition) {
    	// 부모 클래스의 생성자 호출, 변수들이 private으로 선언되어 있으므로 super 사용하여 호출해야 함
        super(date, temperature, condition); 
    }

    @Override // 기본적으로 상속받는 Object 클래스의 toString() 메소드 오버라이드하여 원하는 방식으로 포맷된 문자열 반환
    public String toString() {
        return getDate() + "일 - 온도: " + getTemperature() + "도, 날씨: " + getCondition();
    }
}
