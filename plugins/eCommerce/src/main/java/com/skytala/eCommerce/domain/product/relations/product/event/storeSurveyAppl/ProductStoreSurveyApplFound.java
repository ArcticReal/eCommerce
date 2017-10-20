package com.skytala.eCommerce.domain.product.relations.product.event.storeSurveyAppl;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeSurveyAppl.ProductStoreSurveyAppl;
public class ProductStoreSurveyApplFound implements Event{

	private List<ProductStoreSurveyAppl> productStoreSurveyAppls;

	public ProductStoreSurveyApplFound(List<ProductStoreSurveyAppl> productStoreSurveyAppls) {
		this.productStoreSurveyAppls = productStoreSurveyAppls;
	}

	public List<ProductStoreSurveyAppl> getProductStoreSurveyAppls()	{
		return productStoreSurveyAppls;
	}

}
