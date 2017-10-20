package com.skytala.eCommerce.domain.product.relations.product.event.categoryContent;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryContent.ProductCategoryContent;
public class ProductCategoryContentFound implements Event{

	private List<ProductCategoryContent> productCategoryContents;

	public ProductCategoryContentFound(List<ProductCategoryContent> productCategoryContents) {
		this.productCategoryContents = productCategoryContents;
	}

	public List<ProductCategoryContent> getProductCategoryContents()	{
		return productCategoryContents;
	}

}
