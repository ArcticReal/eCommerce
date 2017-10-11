package com.skytala.eCommerce.domain.product.relations.productMaint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMaint.model.ProductMaint;
public class ProductMaintDeleted implements Event{

	private boolean success;

	public ProductMaintDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
