package com.skytala.eCommerce.domain.product.relations.saleType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.saleType.model.SaleType;
public class SaleTypeAdded implements Event{

	private SaleType addedSaleType;
	private boolean success;

	public SaleTypeAdded(SaleType addedSaleType, boolean success){
		this.addedSaleType = addedSaleType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public SaleType getAddedSaleType() {
		return addedSaleType;
	}

}
