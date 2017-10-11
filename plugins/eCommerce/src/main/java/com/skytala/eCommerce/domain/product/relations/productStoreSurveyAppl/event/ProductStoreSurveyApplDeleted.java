package com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.model.ProductStoreSurveyAppl;
public class ProductStoreSurveyApplDeleted implements Event{

	private boolean success;

	public ProductStoreSurveyApplDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
