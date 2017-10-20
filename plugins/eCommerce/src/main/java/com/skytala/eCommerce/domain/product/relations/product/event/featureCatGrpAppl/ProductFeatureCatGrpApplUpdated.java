package com.skytala.eCommerce.domain.product.relations.product.event.featureCatGrpAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureCatGrpAppl.ProductFeatureCatGrpAppl;
public class ProductFeatureCatGrpApplUpdated implements Event{

	private boolean success;

	public ProductFeatureCatGrpApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
