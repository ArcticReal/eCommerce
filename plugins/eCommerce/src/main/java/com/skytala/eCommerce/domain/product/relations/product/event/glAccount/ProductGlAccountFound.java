package com.skytala.eCommerce.domain.product.relations.product.event.glAccount;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.glAccount.ProductGlAccount;
public class ProductGlAccountFound implements Event{

	private List<ProductGlAccount> productGlAccounts;

	public ProductGlAccountFound(List<ProductGlAccount> productGlAccounts) {
		this.productGlAccounts = productGlAccounts;
	}

	public List<ProductGlAccount> getProductGlAccounts()	{
		return productGlAccounts;
	}

}
