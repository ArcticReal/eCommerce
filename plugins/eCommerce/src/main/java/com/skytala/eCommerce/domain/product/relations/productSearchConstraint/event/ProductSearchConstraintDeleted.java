package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;
public class ProductSearchConstraintDeleted implements Event{

	private boolean success;

	public ProductSearchConstraintDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
