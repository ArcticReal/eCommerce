package com.skytala.eCommerce.domain.product.relations.product.event.promo;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromoProduct;
public class ProductPromoProductFound implements Event{

	private List<ProductPromoProduct> productPromoProducts;

	public ProductPromoProductFound(List<ProductPromoProduct> productPromoProducts) {
		this.productPromoProducts = productPromoProducts;
	}

	public List<ProductPromoProduct> getProductPromoProducts()	{
		return productPromoProducts;
	}

}
