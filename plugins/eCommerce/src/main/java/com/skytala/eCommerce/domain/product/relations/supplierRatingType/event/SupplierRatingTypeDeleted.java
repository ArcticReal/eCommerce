package com.skytala.eCommerce.domain.product.relations.supplierRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.supplierRatingType.model.SupplierRatingType;
public class SupplierRatingTypeDeleted implements Event{

	private boolean success;

	public SupplierRatingTypeDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
