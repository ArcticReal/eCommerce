package com.skytala.eCommerce.domain.product.relations.supplierRatingType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.supplierRatingType.model.SupplierRatingType;
public class SupplierRatingTypeUpdated implements Event{

	private boolean success;

	public SupplierRatingTypeUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
