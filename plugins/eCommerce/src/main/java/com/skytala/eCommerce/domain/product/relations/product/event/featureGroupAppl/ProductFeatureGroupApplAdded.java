package com.skytala.eCommerce.domain.product.relations.product.event.featureGroupAppl;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl.ProductFeatureGroupAppl;
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
