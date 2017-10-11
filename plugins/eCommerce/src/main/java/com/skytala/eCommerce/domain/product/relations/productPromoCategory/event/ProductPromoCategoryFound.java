package com.skytala.eCommerce.domain.product.relations.productPromoCategory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productPromoCategory.model.ProductPromoCategory;
public class ProductPromoCategoryFound implements Event{

	private List<ProductPromoCategory> productPromoCategorys;

	public ProductPromoCategoryFound(List<ProductPromoCategory> productPromoCategorys) {
		this.productPromoCategorys = productPromoCategorys;
	}

	public List<ProductPromoCategory> getProductPromoCategorys()	{
		return productPromoCategorys;
	}

}
