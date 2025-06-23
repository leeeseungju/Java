package fishGame.fish;

import fishGame.Fish;

// Fish 라는 부모 클레스에서 프로퍼티와 getDisplayName 메소드 상속받음
public class Sea​​Bream extends Fish {
	public Sea​​Bream() {
        super("도미", 3, 4, "B", 50);
    }

	// 메소드를 Override하여 해당 클레스의 자체 메소드를 이용
    @Override
    public String getDisplayName() {
        return "[" + grade + "] " + name ;
    }
}
