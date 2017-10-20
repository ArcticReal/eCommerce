package com.skytala.eCommerce.domain.product.relations.product.event.content;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;
public class ProductContentFound implements Event{

	private List<ProductContent> productContents;

	public ProductContentFound(List<ProductContent> productContents) {
		this.productContents = productContents;
	}

	public List<ProductContent> getProductContents()	{
		return productContents;
	}

}
