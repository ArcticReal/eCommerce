package com.skytala.eCommerce.domain.product.relations.productGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productGlAccount.model.ProductGlAccount;
public class ProductGlAccountAdded implements Event{

	private ProductGlAccount addedProductGlAccount;
	private boolean success;

	public ProductGlAccountAdded(ProductGlAccount addedProductGlAccount, boolean success){
		this.addedProductGlAccount = addedProductGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductGlAccount getAddedProductGlAccount() {
		return addedProductGlAccount;
	}

}
