package com.skytala.eCommerce.domain.product.relations.product.event.promoUse;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoUse.ProductPromoUse;
public class ProductPromoUseFound implements Event{

	private List<ProductPromoUse> productPromoUses;

	public ProductPromoUseFound(List<ProductPromoUse> productPromoUses) {
		this.productPromoUses = productPromoUses;
	}

	public List<ProductPromoUse> getProductPromoUses()	{
		return productPromoUses;
	}

}
