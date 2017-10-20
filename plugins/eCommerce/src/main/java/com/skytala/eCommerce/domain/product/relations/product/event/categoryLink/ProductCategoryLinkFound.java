package com.skytala.eCommerce.domain.product.relations.product.event.categoryLink;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryLink.ProductCategoryLink;
public class ProductCategoryLinkFound implements Event{

	private List<ProductCategoryLink> productCategoryLinks;

	public ProductCategoryLinkFound(List<ProductCategoryLink> productCategoryLinks) {
		this.productCategoryLinks = productCategoryLinks;
	}

	public List<ProductCategoryLink> getProductCategoryLinks()	{
		return productCategoryLinks;
	}

}
