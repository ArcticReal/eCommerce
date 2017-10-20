package com.skytala.eCommerce.domain.product.relations.product.event.categoryRollup;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryRollup.ProductCategoryRollup;
public class ProductCategoryRollupAdded implements Event{

	private ProductCategoryRollup addedProductCategoryRollup;
	private boolean success;

	public ProductCategoryRollupAdded(ProductCategoryRollup addedProductCategoryRollup, boolean success){
		this.addedProductCategoryRollup = addedProductCategoryRollup;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryRollup getAddedProductCategoryRollup() {
		return addedProductCategoryRollup;
	}

}
