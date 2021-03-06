package com.skytala.eCommerce.domain.product.relations.product.event.categoryType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryType.ProductCategoryType;
public class ProductCategoryTypeFound implements Event{

	private List<ProductCategoryType> productCategoryTypes;

	public ProductCategoryTypeFound(List<ProductCategoryType> productCategoryTypes) {
		this.productCategoryTypes = productCategoryTypes;
	}

	public List<ProductCategoryType> getProductCategoryTypes()	{
		return productCategoryTypes;
	}

}
