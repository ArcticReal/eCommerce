package com.skytala.eCommerce.domain.product.relations.product.event.featureCategoryAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureCategoryAppl.ProductFeatureCategoryAppl;
public class ProductFeatureCategoryApplDeleted implements Event{

	private boolean success;

	public ProductFeatureCategoryApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
