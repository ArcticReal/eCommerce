package com.skytala.eCommerce.domain.productCategory.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.productCategory.model.ProductCategory;
public class ProductCategoryFound implements Event{

	private List<ProductCategory> productCategorys;

	public ProductCategoryFound(List<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}

	public List<ProductCategory> getProductCategorys()	{
		return productCategorys;
	}

}
