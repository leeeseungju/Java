package javaChip;

import java.util.Scanner;

public class Mall {
	
	// 전역 객체 생성
    ProductInfo[] products = new ProductInfo[999]; // 상품 담을 배열
    ProductInfo[] wishList = new ProductInfo[999]; // 장바구니 배열
    Scanner scanner = new Scanner(System.in);
    
    // 전역 변수 선언
    int productCount = 0;
    int wishCount = 0;

 // 김하현
    public void login() { 
    
	    // 판매자와 구매자의 고정된 ID 및 비밀번호를 정의
	    String sellerId = "kopo";
	    String sellerPw = "1234";
	    String buyerId = "kopo";
	    String buyerPw = "5678";
	
	    System.out.println("\n===== 쇼핑몰 로그인 ====="); // 문자열 앞에 \n을 같이 입력함으로 줄바꿈이 되어 가독성이 좋아짐
	
	    while (true) { // 로그인 루프: 로그인 성공 시까지 반복 실행
		    String role = ""; // 사용자 역할을 저장하기 위한 문자열 변수 선언
	
	        // '판매자' 또는 '구매자' 중 하나를 정확히 입력할 때까지 반복
	        // 정확한 역할을 입력할 때까지 계속 물음
	        while (true) {
	            System.out.print("판매자 / 구매자 선택: ");
	            role = scanner.nextLine();
	            if (role.equals("판매자") || role.equals("구매자")) break; // 입력이 '판매자' 또는 '구매자'일 경우 반복 종료
	            System.out.println("잘못된 입력입니다. 다시 선택하세요.");
	        }
	
	        // 사용자 ID 입력 전 'z'를 입력하면 이전 단계(역할 선택)으로 돌아갈 수 있음
	        System.out.print("ID 입력 (뒤로가기 : z): ");
	        String id = scanner.nextLine();
	        if (id.equalsIgnoreCase("z")) { // 사용자가 'z'를 입력하면 역할 선택으로 되돌아감
	            continue; // while 루프 처음(역할 선택)으로 이동
	        }
	
	        // 비밀번호 입력
	        System.out.print("비밀번호 입력: ");
	        String pw = scanner.nextLine();
	
	        // 역할에 따라 ID, 비밀번호를 확인하고 맞으면 해당 기능 실행
	        if (role.equals("판매자") && id.equals(sellerId) && pw.equals(sellerPw)) {
	            productInput(); // 판매자 로그인 성공: 상품 입력 메서드 호출
	        } else if (role.equals("구매자") && id.equals(buyerId) && pw.equals(buyerPw)) {
	            defaultProducts(); // 기본 상품 메서드 호출
	            buySelect(); // 구매자 로그인 성공: 상품 구매 메서드 호출
	        } else {
	            System.out.println("로그인 실패. 다시 시도하세요.");
	        }
        }
    }
    
    // 최은지 구매자로 로그인시 기본값으로 들어갈 상품 지정
    // 재로그인시 리스트가 중첩되는 상황을 막기 위해 예외처리 실행 
    public void defaultProducts() {
        if (productCount > 0) return; // 이미 상품이 있다면, 기본상품 추가 안함 
            
        String[] defaultList = {
            "치약", "칫솔", "샴푸", "비누", "수건", 
            "세제", "행주", "화장지", "쓰레기봉투", "세탁세제"
        };
        
        int[] defaultPrice = {
            2000, 1500, 5000, 1000, 3000,
            4000, 1000, 5000, 2000, 4500
        };

        //2개 조건: 상품의 길이만큼반복 &  products의 최대 크기를 초과하지 않도록 
        for (int i = 0; i < defaultList.length; i++) { 
        	products[productCount] = new ProductInfo(defaultList[i], defaultPrice[i]);
            productCount++;
        }
    }

    // 최규혁
    public void productInput() { 
    	defaultProducts();
        System.out.println("===== 상품 등록 =====");
        
		// while ( productCount < products.length) < - while문을 쓸 때
		// productCount 를 0 이 아닌 i 로 잡아야 입력한 수까지만 볼 수 있음
		// i 를 0으로 잡으면 null값이 생겨 오류
        for (int i = productCount; i < products.length; i++) {
            System.out.println("상품 등록을 취소하고 뒤로 가시겠습니까?(뒤로가기 : z (로그인 페이지로 이동)" + ", 계속 진행 : r");
            // z, r 만 받게 하기 위헤 while문 무한 루프로 묶어둠
            while (true) {
                String input = scanner.nextLine();
                // z를 누르면 뒤로가기 기능 구현 ( 로그인 메소드로 이동)
                if (input.equalsIgnoreCase("z")) {
                        login();
                        break;
                } else if (input.equalsIgnoreCase("r")) {
                        System.out.println("계속 진행하겠습니다.");
                        break;
                }
                // z 또는 r만 받기 위해 예외 처리
                else {
                        System.out.println("z 또는 r 를 입력해주세요");
                }
            }
            System.out.println("===========================================");
            // 정확한 금액을 받기 위해 예외처리를 및 정확한 금액을 입력할 떄까지 무한루프
            while (true) {
                    try {
                        System.out.print("상품 이름 입력: ");
                        String name = scanner.nextLine();
                        System.out.print("상품 가격 입력: ");
                        int price = Integer.parseInt(scanner.nextLine());
                     // 판매자가 입력한 상품 이름과 가격을  new ProductInfo로 객체를 생성해 products 배열에 값을 추가
                     // 새로운 상품이 추가 될 때마다 productCount 인덱스 번호를 1씩 추가시킴 
                     // 입력이 한 번 끝나면 break로 빠져나와 계속 상품을 입력할 것인지 확인을 하기 위해 종료 메시지를 받음
                        products[productCount++] = new ProductInfo(name, price);
                        break;
                    }
                    catch (NumberFormatException e) {
                        System.out.println("정확한 금액을 입력해주세요");
                    }
           }
            while (true) {
                System.out.print("상품 입력을 종료하시겠습니까? (종료: q / 계속: r): ");
                String quit = scanner.nextLine();
                if (quit.equalsIgnoreCase("q")) {
	            	// q를 입력하면 등록된 상품 목록이 바로 나오고 등록 종료 (위로 올라가 뒤로가기 메시지를 받음)
	                System.out.println("===== 등록된 상품 목록 =====");  
	                //product배열 기준으로 반복문을 돌려 입력된 상품 목록을 출력
	                for (int j = 0; j < products.length; j++) {
	                	ProductInfo product = products[j];
	                	//product 배열 안에 값이 null이 아니라면 출력한다
	                	if (product != null) {
	                		System.out.println((j + 1) + ". " + product.productName + " - " + product.productPrice + "원");
	                	}
					}
	                System.out.println("상품 입력 완료");
	                System.out.println();
	                break; // 상품 등록 종료
                } else if (quit.equalsIgnoreCase("r")) {
                    // break로 빠져 나가 상품 입력 메시지를 받음
                    System.out.println("계속 진행하겠습니다.");
                    System.out.println("===============");
                    break;
                // q 또는 r 만 받기 위해 else로 무한루프 형성
                } else {
                    System.out.println("q 또는 r 을 입력해주세요");
                }
            }
        }
        System.out.println("===== 등록된 상품 목록 =====");
        //products 배열로 범위를 잡았을 때 null값이 생기면 오류가 나기에 productCount로 범위를 잡음
            for (int i = 0; i < productCount; i++) {
                System.out.println((i + 1) + ". " + products[i].productName + " - " + products[i].productPrice + "원");
        }
    }
    
    
    // 이승주_상품 리스트 출력 및 입력값을 받아 상품을 장바구니에 담기, 장바구니 보기, (+예외처리, 뒤로가기), 전체 반복 루프
    public void buySelect() { 
        while (true) { // 입력 반복 루프
            System.out.println("===== 구매 가능한 상품 목록 =====");
            for (int i = 0; i < products.length; i++) { // 등록된 상품의 개수만큼 상품 출력
            	ProductInfo product = products[i];
            	if (product != null) {
            		System.out.println((i + 1) + ". " + product.productName + " - " + product.productPrice + "원");
            	}
            }

            System.out.print("구매할 상품 번호 입력 (w: 장바구니 보기, z: 뒤로 가기): "); // 분기 (1. 구매할 상품 번호 입력, 2. 장바구니, 3. 뒤로 가기)
            String input = scanner.nextLine();

            // w 키 입력 시
            if (input.equalsIgnoreCase("w")) {
                wishList(); // 장바구니 메소드 호출
                continue;
            } else if (input.equalsIgnoreCase("z")) { // z 키 입력 시 뒤로 가기 (로그아웃 메세지 출력)
                System.out.println("로그아웃 하시겠습니까? (y/n)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    System.out.println("로그아웃 되었습니다.");
                    login(); // 초기 로그인 메소드 호출
                    return; // 루프 탈출
                } else if (answer.equalsIgnoreCase("n")) {
                    continue;
                } else { // 예외 처리
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
            }

            int answer;
            // 입력값을 숫자로 바꾸려다 실패할 경우 예외 처리
            try {
            	answer = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
                continue;
            }

            // 숫자를 입력받은 경우 구매하려는 상품의 번호가 상품 배열 내에 있는지 확인
            if (answer < 1 || answer > productCount) {
                System.out.println("존재하지 않는 상품 번호입니다.");
                continue;
            }

            // 선택한 상품을 장바구니에 담기, 인덱스가 0부터 시작하므로 -1 하여 원하는 인덱스 값 불러오기
            ProductInfo selected = products[answer - 1]; 
            System.out.println(selected.productName + " 상품을 장바구니에 담으시겠습니까? (y/n)");
            String confirm = scanner.nextLine(); // 새로운 대답 받을 변수

            if (confirm.equalsIgnoreCase("y")) {
                wishList[wishCount++] = selected; // 장바구니 배열에 추가
                System.out.println("장바구니에 담았습니다.");
            } else if (confirm.equalsIgnoreCase("n")) {
                System.out.println("장바구니가 취소되었습니다. 계속 쇼핑하시겠습니까? (y/n)");
                String again = scanner.nextLine();
                if (again.equalsIgnoreCase("y")) {
                    continue; // 다시 상품 목록 출력, 분기가 많아져서 복잡해져서 GPT 도움을 받음
                } else if (again.equalsIgnoreCase("n")) {
                    System.out.println("쇼핑을 종료합니다.");
                    wishList = new ProductInfo[999]; // 쇼핑몰을 종료하며 장바구니를 비움
                    wishCount = 0;
                    login(); // 초기 로그인 메소드로 돌아가기
                    return;
                } else { // 예외 처리
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
            } else { // 예외 처리
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    
    
    //최은지_장바구니 
    public void wishList() {
            //장바구니가 비어있을 경우 안내 메세지 출력 
        if (wishCount == 0) {
            System.out.println("장바구니가 비어 있습니다.");
            buySelect();
            return; 
        }

        //선택 물품 리스트 출력 
        System.out.println("===== 장바구니 목록 =====");
        int totalPrice = 0;
        for (int i = 0; i < wishCount; i++) {
            ProductInfo product = wishList[i];
            System.out.println((i + 1) + ". " + product.productName + " - " + product.productPrice + "원");
            totalPrice += product.productPrice;
        }
        //선택 물품 총 금액 출력 
        System.out.println("총 금액: " + totalPrice + "원");
        
        //구매 여부를 입력받는 반복문 
        while (true) { 
            System.out.print("구매하시겠습니까? (p: 구매 / q: 상품 페이지로 돌아가기): ");
            String answer = scanner.nextLine();
            
            //p 입력시 구매진행 
            if (answer.equalsIgnoreCase("p")) {
                purchase(totalPrice);
                break; 
             //q 입력시 상품페이지로 돌아가기 
            } else if (answer.equalsIgnoreCase("q")) {
                buySelect();
                break; 
              //잘못 입력시 안내 메세지 출력 
            } else { 
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요. (p / q)");
            }
        }
    }

    
    // 정승인_구매확인 및 구매자 정보 입력
    public void purchase(int totalPrice) {
        // 구매자 주소, 전화번호 받을 변수/ 루프돌리기 위한 변수
        String address = null;
        String phone = null;
        int loopCnt = 999;
        
        // 결제 정보 입력을 알려기 위한 알림
        System.out.println("===== 결제 정보 입력 =====");
        
        // for문을 이용해 주소를 입력 받게함 
        for (int i=0; i < loopCnt; i++) {
                
            // 주소 입력을 요구하는 알림과 주소받을 변수 생성
            System.out.print("주소를 입력하세요(돌아가기 : z): ");
            address = scanner.nextLine();
            
            // 입력값이 비면 재입력 요구 / z누르면 wishList로 / 주소 입력시 루프 깨기
            if (address == null || address == "") {
                    System.out.println("잘못된 입력 입니다. 주소를 입력해 주세요");                        
            } else if (address.equalsIgnoreCase("z")) {
                    wishList();
            } else {
                    break;
            }
        }
        // for문을 이용해 전화번호를 입력 받게함 
        for (int i = 0; i < loopCnt; i++) {
                
            // 전화번호 입력을 요구하는 알림과 전화번호받을 변수 생성
            System.out.print("연락처를 입력하세요(돌아가기 : z): ");
            phone = scanner.nextLine();             
            
            // 입력값이 비면 재입력 요구 / z누르면 wishList로 / 전화번호 입력시 루프 깨기
            if (phone == null || phone == "") {
                    System.out.println("잘못된 입력 입니다. 전화번호를 입력해 주세요");                        
            } else if (phone.equalsIgnoreCase("z")) {
                    wishList();
            } else if (!phone.matches("\\d+")) { // GPT로 고침 확인 필요
                    System.out.println("잘못된 입력 입니다. 전화번호를 입력해 주세요");        
                } else {
                    break;
            }
        }
        
        // 총 금액과 입력한 주소, 연락처 출력
	    System.out.println("===== 구매 완료 =====");
	    System.out.println("총 결제 금액: " + totalPrice + "원");
	    System.out.println("배송지: " + address);
	    System.out.println("연락처: " + phone);
    
	    // 주문은 끝나도 쇼핑이 끝난건 아니니 입력한 상품은 유지하고 위시리스트만 비움
	    wishList = new ProductInfo[10]; 
	    wishCount = 0;
    
	    //구매완료 후 쇼핑을 계속할지 완전히 종료할지 문구 추가
	    System.out.println("=================");
	    System.out.println("쇼핑을 계속 하시겠습니까?(진행: r, 중단: q)");
    
	    // for문 으로 잘못 입력 받은걸 다시 입력하게 함 / r 누르면 buySelect로 q 누르면 login 나머진 다시
	    for (int i = 0; i < loopCnt; i++) {
            String inputBtn = scanner.nextLine();
		    if (inputBtn.equalsIgnoreCase("r")){
		            buySelect();
		    } else if (inputBtn.equalsIgnoreCase("q")) {
		            System.out.println("쇼핑 종료");
		            login(); 
		    } else {
		            System.out.println("잘못된 입력 입니다. 다시 입력해 주세요");        
		    }
		}
    }
}