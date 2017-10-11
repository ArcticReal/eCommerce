package com.skytala.eCommerce.domain.product.relations.productSearchConstraint.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productSearchConstraint.model.ProductSearchConstraint;
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
