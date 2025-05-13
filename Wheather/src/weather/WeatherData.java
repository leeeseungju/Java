package weather;

// 날씨 정보를 저장하기 위한 추상 클래스, 날짜, 온도, 상태를 공통으로 가지는 부모 클래스
public abstract class WeatherData { 
	// 내부에서만 사용할 변수 private 선언하기
    private int date;
    private int temperature;
    private String condition;
    
    // 생성자, 날씨 데이터 초기화
    public WeatherData(int date, int temperature, String condition) {
        this.date = date; // 날짜 데이터
        this.temperature = temperature; // 온도 데이터
        this.condition = condition; // 상태 데이터
    }
    
    // 외부에서 안정적으로 접근, 호출 할 수 있도록 getter/setter 사용
    // 날짜에 대한 getter/setter
    public int getDate() { 
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
    
    // 온도에 대한 getter/setter
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    
    // 상태에 대한 getter/setter
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
