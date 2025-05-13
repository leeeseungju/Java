package fishGame;

//낚은 물고기 리스트를 생성하기 위한 클래스
public class FishList {
    public String fishName;
    public int score;
    
    // 물고기에 대한 이름과 점수를 저장하기 위한 생성자 생성
    public FishList(String fishName, int score) {
        this.fishName = fishName;
        this.score = score;
    }
}