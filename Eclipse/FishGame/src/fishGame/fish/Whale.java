package fishGame.fish;

import fishGame.Fish;

// Fish 라는 부모 클레스에서 프로퍼티와 getDisplayName 메소드 상속받음
public class Whale extends Fish {
    public Whale() {
        super("고래", 1, 1, "S", 100);
    }

    // 메소드를 Override하여 해당 클레스의 자체 메소드를 이용
    @Override
    public String getDisplayName() {
        return "[" + grade + "] " + name ;
    }
}
