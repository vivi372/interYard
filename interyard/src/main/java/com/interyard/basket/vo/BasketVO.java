package com.interyard.basket.vo;

import lombok.Data;

@Data
public class BasketVO {
	int deleteItem;
	long basketNo,goodsNo,orderPrice,dlvyCharge,basketOptNo,optNo,amount,optPrice,categoryNo;
	String id,optName,goodsTitle,goodsMaker,goodsImage,hopeDate,goodsStartDate,goodsEndDate;
	
	
}
