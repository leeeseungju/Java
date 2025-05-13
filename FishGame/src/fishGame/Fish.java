package fishGame;

import java.util.Random;

public abstract class Fish { // 클래스 추상화
	// 각 물고기는 이름, 등장 확률, 잡는 확률, 등급, 점수를 가짐 
    protected String name;
    protected int findRate;
    protected int catchRate;
    protected String grade;
    protected int score;

    // 물고기 생산자를 생성함, 이름, 등장 확률, 잡는 확률, 등급, 점수로 이루어짐
    public Fish(String name, int findRate, int catchRate, String grade, int score) {
        this.name = name;
        this.findRate = findRate;
        this.catchRate = catchRate;
        this.grade = grade;
        this.score = score;
    }

    // 등장시키는 메서드, 랜덤함수를 이용 값을 반환받음 이걸 확률 계산에 이용
    public boolean find() {
        return new Random().nextInt(10) <= findRate;
    }

    // 물고기 잡는 메서드, 랜덤함수를 이용 값을 반환받음 이걸 확률 계산에 이용
    public int catchAttempt() {
        return new Random().nextInt(10);
    }

    // 해당 물고기의 이름을 반환받는 메서드
    public String getName() {
        return name;
    }

    // 해당 물고기의 잡는 확률을 반환받는 메서드
    public int getCatchRate() {
        return catchRate;
    }
    
    // 해당 물고기의 등급을 반환 바든 메서드
    public String getGrade() {
    	return this.grade;
    }

    // 해당 물고기의 이름을 반환 받는 메서드
    public String getDisplayName() {
        return "[" + grade + "] " + name;
    }
}
