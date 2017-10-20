package com.skytala.eCommerce.domain.product.relations.product.event.categoryAttribute;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryAttribute.ProductCategoryAttribute;
public class ProductCategoryAttributeFound implements Event{

	private List<ProductCategoryAttribute> productCategoryAttributes;

	public ProductCategoryAttributeFound(List<ProductCategoryAttribute> productCategoryAttributes) {
		this.productCategoryAttributes = productCategoryAttributes;
	}

	public List<ProductCategoryAttribute> getProductCategoryAttributes()	{
		return productCategoryAttributes;
	}

}
