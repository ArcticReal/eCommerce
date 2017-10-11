package com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCodeEmail.model.ProductPromoCodeEmail;
public class ProductPromoCodeEmailFound implements Event{

	private List<ProductPromoCodeEmail> productPromoCodeEmails;

	public ProductPromoCodeEmailFound(List<ProductPromoCodeEmail> productPromoCodeEmails) {
		this.productPromoCodeEmails = productPromoCodeEmails;
	}

	public List<ProductPromoCodeEmail> getProductPromoCodeEmails()	{
		return productPromoCodeEmails;
	}

}
