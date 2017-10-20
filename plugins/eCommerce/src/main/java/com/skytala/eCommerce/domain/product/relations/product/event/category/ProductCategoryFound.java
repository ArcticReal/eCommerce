package com.skytala.eCommerce.domain.product.relations.product.event.category;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.category.ProductCategory;
public class ProductCategoryFound implements Event{

	private List<ProductCategory> productCategorys;

	public ProductCategoryFound(List<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}

	public List<ProductCategory> getProductCategorys()	{
		return productCategorys;
	}

}
