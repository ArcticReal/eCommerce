package com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.model.ProductCategoryGlAccount;
public class ProductCategoryGlAccountFound implements Event{

	private List<ProductCategoryGlAccount> productCategoryGlAccounts;

	public ProductCategoryGlAccountFound(List<ProductCategoryGlAccount> productCategoryGlAccounts) {
		this.productCategoryGlAccounts = productCategoryGlAccounts;
	}

	public List<ProductCategoryGlAccount> getProductCategoryGlAccounts()	{
		return productCategoryGlAccounts;
	}

}
