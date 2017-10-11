package com.skytala.eCommerce.domain.product.relations.productCategoryLink.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryLink.model.ProductCategoryLink;
public class ProductCategoryLinkAdded implements Event{

	private ProductCategoryLink addedProductCategoryLink;
	private boolean success;

	public ProductCategoryLinkAdded(ProductCategoryLink addedProductCategoryLink, boolean success){
		this.addedProductCategoryLink = addedProductCategoryLink;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryLink getAddedProductCategoryLink() {
		return addedProductCategoryLink;
	}

}