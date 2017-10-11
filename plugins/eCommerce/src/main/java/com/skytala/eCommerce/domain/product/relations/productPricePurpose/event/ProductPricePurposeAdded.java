package com.skytala.eCommerce.domain.product.relations.productPricePurpose.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPricePurpose.model.ProductPricePurpose;
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
