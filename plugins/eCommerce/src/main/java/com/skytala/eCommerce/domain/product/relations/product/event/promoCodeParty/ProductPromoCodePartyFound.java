package com.skytala.eCommerce.domain.product.relations.product.event.promoCodeParty;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoCodeParty.ProductPromoCodeParty;
public class ProductPromoCodePartyFound implements Event{

	private List<ProductPromoCodeParty> productPromoCodePartys;

	public ProductPromoCodePartyFound(List<ProductPromoCodeParty> productPromoCodePartys) {
		this.productPromoCodePartys = productPromoCodePartys;
	}

	public List<ProductPromoCodeParty> getProductPromoCodePartys()	{
		return productPromoCodePartys;
	}

}
