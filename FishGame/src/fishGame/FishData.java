package fishGame;

import java.util.Random;

import fishGame.fish.Blowfish;
import fishGame.fish.Goldfish;
import fishGame.fish.Octopus;
import fishGame.fish.Ray;
import fishGame.fish.Salmon;
import fishGame.fish.Sardine;
import fishGame.fish.Sea​​Bream;
import fishGame.fish.Shark;
import fishGame.fish.Sogari;
import fishGame.fish.Whale;

// fishList에 만들어 둔 물고기를 불러와 저장
public class FishData {
    private static final Fish[] fishList = {
    	new Whale(),
    	new Shark(),
    	new Ray(),
    	new Sea​​Bream(),
        new Octopus(),
        new Sogari(),
        new Blowfish(), 
        new Salmon(), 
        new Sardine(),
        new Goldfish(),
    };
    // 메뉴에서 물고기 도감 보기 선택시 fishList에 저장된 물고기들 반환
    public static Fish[] getAllFish() {
        return fishList;
    }
    // Random 함수를 이용해 fishList에 저장된 물고기중 하나 선택되게 함
    public static Fish getRandomFish() {
        return fishList[new Random().nextInt(fishList.length)];
    }
}
