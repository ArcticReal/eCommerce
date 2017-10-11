package com.skytala.eCommerce.domain.product.relations.productPromoUse.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoUse.model.ProductPromoUse;
public class ProductPromoUseFound implements Event{

	private List<ProductPromoUse> productPromoUses;

	public ProductPromoUseFound(List<ProductPromoUse> productPromoUses) {
		this.productPromoUses = productPromoUses;
	}

	public List<ProductPromoUse> getProductPromoUses()	{
		return productPromoUses;
	}

}
