package com.skytala.eCommerce.domain.product.relations.product.event.priceAutoNotice;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice.ProductPriceAutoNotice;
public class ProductPriceAutoNoticeAdded implements Event{

	private ProductPriceAutoNotice addedProductPriceAutoNotice;
	private boolean success;

	public ProductPriceAutoNoticeAdded(ProductPriceAutoNotice addedProductPriceAutoNotice, boolean success){
		this.addedProductPriceAutoNotice = addedProductPriceAutoNotice;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPriceAutoNotice getAddedProductPriceAutoNotice() {
		return addedProductPriceAutoNotice;
	}

}
