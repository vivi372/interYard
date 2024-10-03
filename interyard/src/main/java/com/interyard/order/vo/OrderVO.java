package com.interyard.order.vo;

import lombok.Data;

@Data
public class OrderVO {	
	long orderNo;
	long orderPrice;
	long dlvyCharge;
	String orderState;
	String cancleReason;
	String id;
	String name;
	String memberTel;
	String payWay;
	String payDetail;
	String hopeDate;
	String orderDate;
	String confirmDate;
	long dlvyAddrNo;
	String dlvyMemo;
	long postNo;
	String recipient;
	String dlvyName;
	String tel;
	String addr;
	String addrDetail;
	long goodsNo;
	String goodsTitle;
	String goodsImage;
	String goodsPublicher;
	String goodsStatus;
	long goodsPrice;
	long goodsCost;
	long categoryNo;
	String categoryName;
	long orderOptNo;
	String optName;
	long optNo;
	long amount;
	
	//옵션이 관련된 주문을 알려주는 변수
	long order;
	long lefOrder;
	long basketNo;
	//다중 주문시 전체 주문 금액 
	long totalCheckPrice;
	long totalCheckDlvyCharge;
	//다중 결제인지 판단 변수
	boolean isMultiOrder;
	
	public void splitAddrDetail() {
		if(this.addr != null) {
			String addr = this.addr;
			this.setAddrDetail(addr.substring(addr.indexOf('/')+1));
			this.setAddr(addr.substring(0,addr.indexOf('/')-1));
		}
	}
	
}
