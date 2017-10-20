package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;
public class ProductStoreGroupMemberAdded implements Event{

	private ProductStoreGroupMember addedProductStoreGroupMember;
	private boolean success;

	public ProductStoreGroupMemberAdded(ProductStoreGroupMember addedProductStoreGroupMember, boolean success){
		this.addedProductStoreGroupMember = addedProductStoreGroupMember;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProductStoreGroupMember getAddedProductStoreGroupMember() {
		return addedProductStoreGroupMember;
	}

}
