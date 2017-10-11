package com.skytala.eCommerce.domain.product.relations.productMaint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productMaint.model.ProductMaint;
public class ProductMaintUpdated implements Event{

	private boolean success;

	public ProductMaintUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
