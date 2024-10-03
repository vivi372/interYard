package com.interyard.order.list;

import java.util.ArrayList;
import com.interyard.order.vo.OrderVO;

/**
 * ArrayList<OrderVO>를 상속 받아 메서드를 추가해 확장시킨 클래스
 */
public class OrderList extends ArrayList<OrderVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderList splitStack() {
		OrderList optList = new OrderList();
		for(OrderVO vo: this) {			
			if(vo.getOrder() == 0)
			optList.add(vo);			
		}				
		for(int i=this.size()-1; i >= 0; i--) {		
			System.out.println(i);
			if(this.get(i).getOrder() == 0)
				this.remove(i);					
		}
		//System.out.println(this);
		return optList;
	}
	
	/**
	 * 옵션 정보와 주문 정보들이 담겨져있는 OrderList를 서로 분리하는 메서드
	 * @return 옵션 정보들이 담겨져있는 OrderList 반환
	 */
	public OrderList splitList() {
		//System.out.println("splitList before - "+this);
		//옵션 정보들을 담을 orderList 생성
		OrderList optList = new OrderList();
		//자기한테 있는 옵션 정보를 optList 로 옮겨 담는다. 
		for(OrderVO vo: this) {
			OrderVO opt = new OrderVO();
			opt.setOptName(vo.getOptName());			
			opt.setOptNo(vo.getOptNo());
			opt.setAmount(vo.getAmount());
			opt.setOrderNo(vo.getOrderNo());
			optList.add(opt);			
		}
		//이전 인덱스와 현재 인덱스의 주문번호가 같으면 현재 인덱스를 list에서 지워버린다.
		for(int i=this.size()-1; i >= 1; i--) {					
			if(this.get(i).getOrderNo() == this.get(i-1).getOrderNo())
				this.remove(i);					
		}
		//System.out.println("splitList after - "+this);
		return optList;
	}
	

}
