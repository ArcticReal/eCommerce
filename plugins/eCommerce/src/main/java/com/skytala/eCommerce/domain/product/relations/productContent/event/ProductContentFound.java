package com.skytala.eCommerce.domain.product.relations.productContent.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productContent.model.ProductContent;
public class ProductContentFound implements Event{

	private List<ProductContent> productContents;

	public ProductContentFound(List<ProductContent> productContents) {
		this.productContents = productContents;
	}

	public List<ProductContent> getProductContents()	{
		return productContents;
	}

}
