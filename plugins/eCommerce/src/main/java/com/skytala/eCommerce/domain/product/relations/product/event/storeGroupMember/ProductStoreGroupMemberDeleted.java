package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;
public class ProductStoreGroupMemberDeleted implements Event{

	private boolean success;

	public ProductStoreGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
