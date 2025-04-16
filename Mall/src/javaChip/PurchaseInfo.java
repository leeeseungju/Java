package javaChip;

public class PurchaseInfo {	
	public int totalPrice;
	public String address;
	public String phNumber;
	public int orderNumber;
	
	public PurchaseInfo() {
    }
	
	PurchaseInfo(int orderNumber, int totalPrice, String address, String phNumber) {
		this.totalPrice = totalPrice;
		this.address = address;
		this.phNumber = phNumber;
		this.orderNumber = orderNumber;
	}
	 
    public String toString() {
        return "구매 정보 {" +
               "주문번호=" + orderNumber +
               ", 총 금액=" + totalPrice + "원" +
               ", 주소='" + address + '\'' +
               ", 연락처='" + phNumber + '\'' +
               '}';
    }
}