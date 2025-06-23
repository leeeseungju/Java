package fishGame;

// 유저 정보 관리 클래스
public class User {
    private String name;
    private int score;
    private FishList[] caughtFish = new FishList[40]; // 잡은 물고기 리스트
    private int catchCount = 0; // 잡은 물고기 수

    // 이름을 받아 유저 객체 생성
    public User(String name) {
        this.name = name;
        this.score = 0;
    }
    
    // 유저 이름 반환
    public String getName() {
        return name;
    }
    
    // 유저 최고 점수 반환 (Getter)
    public int getScore() {
        return score;
    }
    
    // 유저 점수 설정 (Setter)
    public void setScore(int score) {
        this.score = score;
    }
    
    // 현재 점수보다 높은 점수를 넘겼을 때 최고 점수 갱신
    public void updateScoreIfHigher(int newScore) {
        if (newScore > this.score) {
            this.score = newScore;
        }
    }

    // 물고기를 잡을 시 잡은 마리수 추가
    public void catchFish(FishList fish) {
        if (catchCount < 40) {
            caughtFish[catchCount++] = fish;
        }
    }
    
    // 잡은 물고기 목록 반환
    public FishList[] getCaughtFish() {
        return caughtFish;
    }
    
    // 현재 잡은 물고기 수 반환
    public int getCatchCount() {
        return catchCount;
    }
    
    // 물고기 리스트 초기화 (유저 변경시)
    public void resetCaughtFish() {
        caughtFish = new FishList[40];
        catchCount = 0;
    }
}
