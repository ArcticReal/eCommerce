package com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeSurveyAppl.ProductStoreSurveyAppl;
public class ProductStoreSurveyApplUpdated implements Event{

	private boolean success;

	public ProductStoreSurveyApplUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
