package com.interyard.basket.service;



import com.interyard.basket.dao.BasketDAO;
import com.interyard.basket.list.BasketList;
import com.interyard.basket.vo.BasketVO;
import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;

/**
 * 장바구니 리스트 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class BasketListService implements Service {
	private BasketDAO dao;
	
	/**
	 * 장바구니 리스트 처리를 진행하는 메서드
	 */
	@Override
	public BasketList service(Object obj) throws Exception {		
		//db에서 장바구니 리스트 정보를 가져온다.
		BasketList list = dao.list((String)obj);
		//db에서 장바구니를 자동 삭제하고 삭제된 장바구니 수를 가져온다.
		int deleteItem = dao.deleteList((String)obj);
		try {
			//자동삭제된 장바구니 수를 리스트에 넣는다.
			list.get(0).setDeleteItem(deleteItem);
		} catch (Exception e) {
			//만약 dao에서 리스트에 아무것도 담지 못 했다면 vo를 만들어 직접 추가한다.
			BasketVO vo = new BasketVO();
			vo.setDeleteItem(deleteItem);
			list.add(vo);
		}
		return list;
	}
	
	
	@Override
	public void setDAO(DAO dao) {		
		this.dao = (BasketDAO) dao;
	}

}
