package com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreSurveyAppl.model.ProductStoreSurveyAppl;
public class ProductStoreSurveyApplAdded implements Event{

	private ProductStoreSurveyAppl addedProductStoreSurveyAppl;
	private boolean success;

	public ProductStoreSurveyApplAdded(ProductStoreSurveyAppl addedProductStoreSurveyAppl, boolean success){
		this.addedProductStoreSurveyAppl = addedProductStoreSurveyAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreSurveyAppl getAddedProductStoreSurveyAppl() {
		return addedProductStoreSurveyAppl;
	}

}
