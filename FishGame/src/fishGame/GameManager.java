package fishGame;

import java.util.Scanner;

public class GameManager { // 매인 매뉴, 물고기 찾기, 물고기 낚기 시도, 잡은 물고기 리스트, 게임 종료 구현
    private Scanner sc = new Scanner(System.in); // 매뉴 선택을 받기 위한 스케너
    private int tryCount = 0; // 낚시 시도 횟수
    private boolean isRunning = true; // while 문 종료를 위한 변수
    private User[] users = new User[100]; // 유저 배열
    private int userCount = 0; // 현재 유저 수
    private User currentUser; // 현재 로그인한 유저

    public void start() { // 게임 매뉴
        System.out.print("닉네임을 입력하세요 >> ");
        String userName = sc.nextLine(); // 닉네임 입력받기

        // 기존 유저 있는지 확인
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(userName)) {
                currentUser = users[i];
                break;
            }
        }

        // 없으면 새로 생성
        if (currentUser == null) {
            currentUser = new User(userName);
            users[userCount++] = currentUser;
        }

        System.out.println("안녕하세요, " + currentUser.getName() + "님! 물고기를 잡아봐요!");

        // while 문으로 선택사항을 반복하게끔
        while (isRunning) {
            System.out.println("\n=== " + currentUser.getName() + ", 물고기를 잡아보자! ===");
            System.out.println("1. 낚시대 던지기");
            System.out.println("2. 잡은 물고기 보기");
            System.out.println("3. 도감 보기");
            System.out.println("4. 유저 변경");
            System.out.println("5. 종료");
            System.out.print("선택 >> ");
            int input = sc.nextInt();
            sc.nextLine(); // 개행 제거

            if (input == 1) {
                searchFish();
            } else if (input == 2) {
                showCaught();
            } else if (input == 3) {
                showWholeFishList();
            } else if (input == 4) {
                switchUser();
            } else if (input == 5) {
                quit();
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    // 물고기 찾기 메소드
    private void searchFish() {
    	// 낚시 시도 횟수 표기를 위한 변수
        tryCount++;
        
        // 일정 낚시 횟수 이하일때 정상 진행 하게끔
        if (tryCount < 20) {
        	// 현재 낚시 시도 횟수를 표기
            System.out.println("\n<" + tryCount + "번째 시도>");
            // 준비된 fishlist에서 랜덤하게 물고기 가져옴
            Fish fish = FishData.getRandomFish();

            // 특정 물고기 등장시 아스키 아트 출력
            if (fish.find()) {
            	// 특정 물고기인지 확인
            	if (fish.getName().equals("고래")) { 
            	// 등장 물고기 출력
                System.out.println(fish.getDisplayName() + "가(이) 등장했다!");  
                System.out.println(
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀      ⠀⠀⠀⠀⠀⠀⠀⣀⣠⠤⢾⣞⣿⣿⣿⣶\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀     ⠀⠀⠀⠀⠀⠀⣀⡤⠔⠚⠉⢁⣤⣶⣾⣿⣟⠻⢇⣿\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    ⠀⠀⠀⠀⣀⡤⠒⠋⠉⠀⠀⠀⣠⣶⣿⣿⡿⢋⡴⠟⣾⡾⠉\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⠀⣀⡴⠚⠁⠀⠀⠀⠀⣠⣴⣾⣿⢟⡿⢋⡴⠛⣠⢾⠏⠁⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠀⢀⡴⠚⠁⠀⠀⠀⠀⢠⣾⣿⠿⡟⢋⠔⢁⣴⠟⢁⡴⣱⠋⠀⠀⠀\n" +
                        "⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⢀⡴⠋⠀⠀⠀⠀⣤⢶⣶⣾⡟⢠⠋⢠⠏⡰⢻⠋⡠⢋⡞⠁⠀⠀⠀⠀\n" +
                        "⡏⢧⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⢀⡴⠋⠀⢀⣰⣶⣿⣷⣇⣾⣿⢿⣤⠃⢠⢏⡞⣡⢃⣞⣡⠋⠀⠀⠀⠀⠀⠀\n" +
                        "⡇⠈⠈⠳⢦⡀⠀⠀⢀⣤⣠⣴⠗⠛⠀⢠⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⣴⠋⠁⣠⣿⣿⣿⢟⣽⡿⢃⡴⢃⠞⣰⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⢻⡄⠀⠀⠸⢿⣿⡶⣾⡍⠉⠁⠀⠀⣠⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠋⠀⠀⠀⣸⠁⠀⢠⣾⣿⣯⣕⣿⣯⠖⢉⡴⢋⣼⣽⣵⣯⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠈⢿⣆⡀⠀⢺⣿⡇⠻⣿⣄⣀⣤⡾⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠎⠀⠀⠀⢠⣼⡇⠀⠀⠀⠻⣯⢛⡵⠚⢁⡴⠊⣠⣾⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠙⠿⣶⣾⣿⣇⠀⢿⣿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣾⠁⠀⠀⠀⢠⣿⣿⡇⠀⠀⠀⢡⣿⠏⣀⠔⢋⡠⣪⣿⣿⣿⠟⠻⠇⡼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠈⠉⢻⡄⠈⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⠇⠀⠀⣀⣰⣿⣿⣿⠀⠀⠀⠀⣆⣿⡏⢁⣴⣯⡾⠋⣿⣿⡁⠀⠀⠀⠳⡆⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠈⢿⠀⠀⠙⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣾⡿⠋⠀⢀⣠⣶⣿⣯⡿⣿⡇⠀⠀⠀⠠⡽⣷⢞⣽⡿⠋⠀⠀⠘⣿⠿⠂⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠈⢳⡀⠀⢲⣾⣿⠓⠲⢤⣤⣤⠤⠔⣲⠟⠛⠋⠁⢀⣴⣴⡿⣿⣽⣿⣿⣿⣿⡁⠀⠀⠀⢰⣻⡿⠟⠁⠀⠀⠀⠀⠈⣧⠀⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣦⣄⡙⠁⠀⠐⠿⠿⠀⠀⠀⠀⠀⠐⠿⠿⢾⣻⣴⣿⣿⡿⠿⠻⣿⡿⠀⠀⠀⠀⣾⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠘⣇⠀⣼⡿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣿⣶⣾⣿⣶⣶⣦⣤⣤⣴⣖⣶⣾⡿⠿⠛⢉⣀⣤⣴⣶⣿⡇⠀⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⢰⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⢿⣻⣿⣿⣿⠛⣭⣴⣒⣒⣚⣛⣯⡭⠽⠛⠋⣿⡇⠀⠀⢠⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡿⢠⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀  ⠀⠀⠀⢻⣷⠀⢀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣞⣀⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀⠸⣿⣀⣮⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⢿⣿⣻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠘⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠘⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                    );
            	}
            	// 특정 물고기인지 확인
                else if (fish.getName().equals("상어")) { 
                	// 등장 물고기 출력 
                    System.out.println(fish.getDisplayName() + "가(이) 등장했다!"); 
                    System.out.println(
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⢠⣾⣿⣏⠉⠉⠉⠉⠉⢡⣶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠻⢿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡄⠀\n" +
                            "⠈⣿⣿⣿⣿⣦⣽⣦⡀⠀⠀⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⠀⠀\n" +
                            "⠀⠘⢿⣿⣿⣿⣿⣿⣿⣦⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⠇⠀⠀\n" +
                            "⠀⠀⠈⠻⣿⣿⣿⣿⡟⢿⠻⠛⠙⠉⠋⠛⠳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⡟⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠈⠙⢿⡇⣠⣤⣶⣶⣾⡉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣰⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠾⢇⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⠃⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠱⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠤⢤⣀⣀⣀⣀⣀⣠⣤⣤⣤⣬⣭⣿⣿⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣶⣤⣄⣀⣀⣠⣴⣾⣿⣿⣿⣷⣤⣀⡀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣾⣿⣿⣿⣿⡿⠿⠛⠛⠻⣿⣿⣿⣿⣇⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣤⣤⣘⡛⠿⢿⡿⠟⠛⠉⠁⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣦⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⢿⣿⣿⣿⣿⣿⣶⣦⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⡄⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⠿⠛⠉⠁⠀⠈⠉⠙⠛⠛⠻⠿⠿⠿⠿⠛⠃⠀⠀⠀⠉⠉⠉⠛⠛⠛⠿⠿⠿⣶⣦⣄⡀⠀⠀⠀⠀⠀⠈⠙⠛⠂\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠿⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                        );

                } else {
                    // 등장한 물고기 출력해서 알림
                    System.out.println(fish.getDisplayName() + "가(이) 등장했다!");
                    System.out.println();
                    System.out.println("1. 낚시하기");
                    System.out.println("2. 뒤로가기");
                    System.out.print("선택 >> ");
                    int choice = sc.nextInt();

                    // 1 선택 시 물고기 잡기
                    if (choice == 1) {
                        // catchAttempt 메서드로 랜덤값 생성
                        int attempt = fish.catchAttempt();
                        // 생성된 값과 물고기가 가진 값을 비교해 잡기 성공여부 결정
                        if (attempt <= fish.getCatchRate()) {
                            // 잡은 물고기 및 확률 출력
                            System.out.println("\n[성공] " + fish.getDisplayName() + "를 잡았다!");
                            System.out.println((fish.getCatchRate() * 10) + "% 확률로 잡기 성공!");
                            // 물고기가 가진 점수 출력
                            System.out.println("+" + fish.score + "점 획득!");
                            // 레벨 체크 (count 증가 전)
                            int beforeLevel = getLevel();
                            currentUser.catchFish(new FishList(fish.getDisplayName(), fish.score));
                            // 레벨 체크 (count 증가 후)
                            int afterLevel = getLevel();
                            // 점수 비교 후 일정 이상 시 칭호 시스템으로 칭호 출력
                            if (afterLevel > beforeLevel) {
                                System.out.println("\n>>" + getLevelText() + "에 도달했습니다!<<");
                            }
                        } else {
                            // 성공여부 판단 후 실패시 도망쳤다는 문구 및 퍼센트 출력
                            System.out.println("\n[실패]" + fish.getDisplayName() + "가(이) 도망쳤다!");
                            System.out.println(((9 - fish.getCatchRate()) * 10) + "% 확률로 잡기 실패!");
                        }
                    } else if (choice == 2) {
                        // 2번 선택시 매뉴로 돌아가기
                        System.out.println("낚시대를 건졌습니다.");
                    } else {
                        // 입력 체크 후 잘못 입력 시 재시도 요청
                        System.out.println("잘못된 입력입니다.");
                    }
                }
            } else {
                // 물고기 찾기 확률 체크 후 실패시 출력
                System.out.println("아무것도 보이지 않는다...");
            }
        } else {
            // 일정 횟수 넘기면 끝내기
            System.out.println("밤이 깊었다. 집으로 돌아가자. . .");
            quit();
        }
    }
    // 낚시 횟수가 일정 이상 넘길 시 값 반환, 칭호(레벨) 시스템에 이용
    private int getLevel() {
        int count = currentUser.getCatchCount();
        if (count >= 10) return 3;
        else if (count >= 5) return 2;
        else return 1;
    }

    // 낚시 횟수를 비교해 칭호(레벨) 표기하는 메서드
    private String getLevelText() {
    	// 기존 getLevel() 메서드 호출
    	int level = getLevel();
        // return 받은 값이 1, 2, 3인지 비교해 출력값 다르게 함
        if (level == 1) return "Lv.1 (초심자)";
        else if (level == 2) return "Lv.2 (중수)";
        else return "Lv.3 (고수)";
    }

    // 특정 유저 기준 레벨 계산
    private int getLevel(User user) {
        int count = user.getCatchCount();
        if (count >= 10) return 3;
        else if (count >= 5) return 2;
        else return 1;
    }

    // 특정 레벨 숫자를 텍스트로 반환
    private String getLevelText(int level) {
        if (level == 1) return "Lv.1 (초심자)";
        else if (level == 2) return "Lv.2 (중수)";
        else return "Lv.3 (고수)";
    }
    
    // 특정 유저의 잡은 물고기와 점수 및 레벨을 출력하는 메서드
    private void printUserCatch(User user) {
        System.out.println("\n[ " + user.getName() + "의 잡은 물고기 목록 ]");

        // 유저의 레벨 계산 및 출력
        int level = getLevel(user);
        System.out.println("현재 레벨: " + getLevelText(level));

        // 잡은 물고기 목록 가져오기
        int count = user.getCatchCount();
        FishList[] caughtFish = user.getCaughtFish();

        // 잡은 물고기가 없으면 안내 후 종료
        if (count == 0) {
            System.out.println("아직 아무 물고기도 못잡았다!");
            return;
        }

        // 중복 출력을 피하기 위한 배열
        String[] printed = new String[count];
        int printedCount = 0; // printed 배열에 저장된 고유 물고기 이름 수
        int totalScore = 0; // 유저의 총 점수 계산용

        // 잡은 물고기 전체 순회
        for (int i = 0; i < count; i++) {
            String currentName = caughtFish[i].fishName; // 현재 물고기 이름
            totalScore += caughtFish[i].score; // 점수 누적

            // 이미 출력된 물고기인지 확인
            boolean alreadyPrinted = false;
            for (int j = 0; j < printedCount; j++) {
                if (printed[j].equals(currentName)) {
                    alreadyPrinted = true;
                    break;
                }
            }

            // 처음 출력하는 물고기인 경우
            if (!alreadyPrinted) {
                int duplicate = 1; // 동일 물고기 마리 수 카운트

                // 이후 목록에서 같은 이름의 물고기 수 체크
                for (int k = i + 1; k < count; k++) {
                    if (caughtFish[k].fishName.equals(currentName)) {
                        duplicate++;
                    }
                }

                // 출력 형식: 한 마리면 이름만, 여러 마리면 이름 x 개수
                if (duplicate == 1) {
                    System.out.println("- " + currentName);
                } else {
                    System.out.println("- " + currentName + " x " + duplicate);
                }

                // 중복 방지를 위해 printed 배열에 이름 저장
                printed[printedCount++] = currentName;
            }
        }

        // 유저의 총 점수 출력
        System.out.println("총 점수: " + totalScore + "점");

        // 유저가 기록한 최고 점수보다 크면 갱신
        user.updateScoreIfHigher(totalScore);
    }

    // 잡은 물고기와 레벨 보여주는 리스트 (currentUser 기준)
    private void showCaught() {
        System.out.println("\n[ " + currentUser.getName() + "의 잡은 물고기 목록 ]");
        printUserCatch(currentUser); // 위에서 만든 공통 출력 메서드 사용

        // 게임 종료 조건 확인 (낚시 시도 >= 20 && 아무 것도 못잡음)
        if (tryCount >= 20 && currentUser.getCatchCount() == 0) {
            System.out.println("오늘은 굶어야겠다. . .");
        }
    }

    // 전체 물고기 도감 출력
    private void showWholeFishList() {
        System.out.println("\n[ 전체 물고기 도감 ]");
        // FishData로 부터 이름, 등급, 점수를 불러옴
        for (Fish fish : FishData.getAllFish()) {
            String name = fish.getName();
            String grade = fish.getGrade();
            int score = fish.score;
            System.out.println("- [" + grade + "] " + name + " (점수: " + score + ")");
        }
    }

    // 유저 전환 메서드
    private void switchUser() {
        System.out.print("새 닉네임을 입력하세요 >> ");
        String name = sc.nextLine();
        currentUser = null;

        // 기존 유저 탐색
        for (int i = 0; i < userCount; i++) {
            if (users[i].getName().equals(name)) {
                currentUser = users[i];
                break;
            }
        }

        // 없으면 새 유저 생성
        if (currentUser == null) {
            currentUser = new User(name);
            users[userCount++] = currentUser;
            System.out.println("새 유저 생성 완료!");
        }

        System.out.println("[" + currentUser.getName() + "]님으로 전환되었습니다.");
        tryCount = 0; // 낚시 횟수 초기화
    }

    // 게임 종료 처리
    private void quit() {
        System.out.println("\n=====게임 종료=====");

        // 모든 유저에 대해 반복하여 낚시 결과 출력
        for (int i = 0; i < userCount; i++) {
            printUserCatch(users[i]); // 공통 출력 메서드 사용
        }

        showScoreBoard(); // 모든 유저 점수 출력
        isRunning = false; // 게임 루프 종료 조건 설정
    }

    // 점수판 출력
    private void showScoreBoard() {
        System.out.println("\n=== 최고 점수판 ===");
        for (int i = 0; i < userCount; i++) {
            System.out.println(users[i].getName() + ": " + users[i].getScore() + "점"); // User 클래스에서 설정한 점수 겟터셋터 불러오기
        }
    }
}
