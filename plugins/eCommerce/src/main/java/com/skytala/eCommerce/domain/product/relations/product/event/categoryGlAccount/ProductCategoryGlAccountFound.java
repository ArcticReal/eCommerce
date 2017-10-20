package com.skytala.eCommerce.domain.product.relations.product.event.categoryGlAccount;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.categoryGlAccount.ProductCategoryGlAccount;
public class ProductCategoryGlAccountFound implements Event{

	private List<ProductCategoryGlAccount> productCategoryGlAccounts;

	public ProductCategoryGlAccountFound(List<ProductCategoryGlAccount> productCategoryGlAccounts) {
		this.productCategoryGlAccounts = productCategoryGlAccounts;
	}

	public List<ProductCategoryGlAccount> getProductCategoryGlAccounts()	{
		return productCategoryGlAccounts;
	}

}
