package com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productFeatureGroupAppl.model.ProductFeatureGroupAppl;
public class ProductFeatureGroupApplAdded implements Event{

	private ProductFeatureGroupAppl addedProductFeatureGroupAppl;
	private boolean success;

	public ProductFeatureGroupApplAdded(ProductFeatureGroupAppl addedProductFeatureGroupAppl, boolean success){
		this.addedProductFeatureGroupAppl = addedProductFeatureGroupAppl;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductFeatureGroupAppl getAddedProductFeatureGroupAppl() {
		return addedProductFeatureGroupAppl;
	}

}
