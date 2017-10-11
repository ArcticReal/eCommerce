package com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productCategoryGlAccount.model.ProductCategoryGlAccount;
public class ProductCategoryGlAccountAdded implements Event{

	private ProductCategoryGlAccount addedProductCategoryGlAccount;
	private boolean success;

	public ProductCategoryGlAccountAdded(ProductCategoryGlAccount addedProductCategoryGlAccount, boolean success){
		this.addedProductCategoryGlAccount = addedProductCategoryGlAccount;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductCategoryGlAccount getAddedProductCategoryGlAccount() {
		return addedProductCategoryGlAccount;
	}

}
