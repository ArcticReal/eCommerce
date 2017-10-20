package com.skytala.eCommerce.domain.product.relations.product.event.searchConstraint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.searchConstraint.ProductSearchConstraint;
public class ProductSearchConstraintDeleted implements Event{

	private boolean success;

	public ProductSearchConstraintDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
