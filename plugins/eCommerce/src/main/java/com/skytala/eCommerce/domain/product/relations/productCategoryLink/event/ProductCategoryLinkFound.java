package com.skytala.eCommerce.domain.product.relations.productCategoryLink.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;
public class ProductCategoryLinkFound implements Event{

	private List<ProductCategoryLink> productCategoryLinks;

	public ProductCategoryLinkFound(List<ProductCategoryLink> productCategoryLinks) {
		this.productCategoryLinks = productCategoryLinks;
	}

	public List<ProductCategoryLink> getProductCategoryLinks()	{
		return productCategoryLinks;
	}

}
