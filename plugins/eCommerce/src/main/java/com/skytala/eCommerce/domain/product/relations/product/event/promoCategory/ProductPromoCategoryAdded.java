package com.skytala.eCommerce.domain.product.relations.product.event.promoCategory;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCategory.ProductPromoCategory;
public class ProductPromoCategoryAdded implements Event{

	private ProductPromoCategory addedProductPromoCategory;
	private boolean success;

	public ProductPromoCategoryAdded(ProductPromoCategory addedProductPromoCategory, boolean success){
		this.addedProductPromoCategory = addedProductPromoCategory;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductPromoCategory getAddedProductPromoCategory() {
		return addedProductPromoCategory;
	}

}
