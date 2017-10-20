package com.skytala.eCommerce.domain.product.relations.product.event.featureCatGrpAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureCatGrpAppl.ProductFeatureCatGrpAppl;
public class ProductFeatureCatGrpApplDeleted implements Event{

	private boolean success;

	public ProductFeatureCatGrpApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
