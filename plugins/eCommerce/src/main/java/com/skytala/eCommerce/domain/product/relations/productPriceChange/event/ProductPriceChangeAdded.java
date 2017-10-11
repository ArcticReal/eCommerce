package com.skytala.eCommerce.domain.product.relations.productPriceChange.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPriceChange.model.ProductPriceChange;
public class ProductPriceChangeAdded implements Event{

	private ProductPriceChange addedProductPriceChange;
	private boolean success;

	public ProductPriceChangeAdded(ProductPriceChange addedProductPriceChange, boolean success){
		this.addedProductPriceChange = addedProductPriceChange;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceChange getAddedProductPriceChange() {
		return addedProductPriceChange;
	}

}
