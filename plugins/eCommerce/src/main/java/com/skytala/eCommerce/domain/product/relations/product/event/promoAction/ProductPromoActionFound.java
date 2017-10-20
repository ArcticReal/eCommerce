package com.skytala.eCommerce.domain.product.relations.product.event.promoAction;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoAction.ProductPromoAction;
public class ProductPromoActionFound implements Event{

	private List<ProductPromoAction> productPromoActions;

	public ProductPromoActionFound(List<ProductPromoAction> productPromoActions) {
		this.productPromoActions = productPromoActions;
	}

	public List<ProductPromoAction> getProductPromoActions()	{
		return productPromoActions;
	}

}
