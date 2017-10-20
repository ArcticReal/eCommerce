package com.skytala.eCommerce.domain.product.relations.product.event.searchConstraint;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.searchConstraint.ProductSearchConstraint;
public class ProductSearchConstraintAdded implements Event{

	private ProductSearchConstraint addedProductSearchConstraint;
	private boolean success;

	public ProductSearchConstraintAdded(ProductSearchConstraint addedProductSearchConstraint, boolean success){
		this.addedProductSearchConstraint = addedProductSearchConstraint;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductSearchConstraint getAddedProductSearchConstraint() {
		return addedProductSearchConstraint;
	}

}
