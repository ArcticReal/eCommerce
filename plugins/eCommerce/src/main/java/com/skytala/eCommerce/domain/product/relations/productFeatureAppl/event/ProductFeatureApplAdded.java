package com.skytala.eCommerce.domain.product.relations.productFeatureAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureAppl.model.ProductFeatureAppl;
public class ProductFeatureApplAdded implements Event{

	private ProductFeatureAppl addedProductFeatureAppl;
	private boolean success;

	public ProductFeatureApplAdded(ProductFeatureAppl addedProductFeatureAppl, boolean success){
		this.addedProductFeatureAppl = addedProductFeatureAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureAppl getAddedProductFeatureAppl() {
		return addedProductFeatureAppl;
	}

}
