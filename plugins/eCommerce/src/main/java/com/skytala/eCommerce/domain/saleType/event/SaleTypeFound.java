package com.skytala.eCommerce.domain.saleType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.saleType.model.SaleType;
public class SaleTypeFound implements Event{

	private List<SaleType> saleTypes;

	public SaleTypeFound(List<SaleType> saleTypes) {
		this.saleTypes = saleTypes;
	}

	public List<SaleType> getSaleTypes()	{
		return saleTypes;
	}

}
