package com.skytala.eCommerce.domain.product.relations.product.event.promoContent;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;
public class ProductPromoContentFound implements Event{

	private List<ProductPromoContent> productPromoContents;

	public ProductPromoContentFound(List<ProductPromoContent> productPromoContents) {
		this.productPromoContents = productPromoContents;
	}

	public List<ProductPromoContent> getProductPromoContents()	{
		return productPromoContents;
	}

}
