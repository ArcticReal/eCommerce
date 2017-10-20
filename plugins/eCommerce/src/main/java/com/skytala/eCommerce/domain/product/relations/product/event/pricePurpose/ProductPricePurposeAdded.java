package com.skytala.eCommerce.domain.product.relations.product.event.pricePurpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.pricePurpose.ProductPricePurpose;
public class ProductPricePurposeAdded implements Event{

	private ProductPricePurpose addedProductPricePurpose;
	private boolean success;

	public ProductPricePurposeAdded(ProductPricePurpose addedProductPricePurpose, boolean success){
		this.addedProductPricePurpose = addedProductPricePurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPricePurpose getAddedProductPricePurpose() {
		return addedProductPricePurpose;
	}

}
