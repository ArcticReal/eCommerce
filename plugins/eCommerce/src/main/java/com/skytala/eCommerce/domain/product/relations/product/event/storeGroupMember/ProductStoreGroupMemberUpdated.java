package com.skytala.eCommerce.domain.product.relations.product.event.storeGroupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.product.model.storeGroupMember.ProductStoreGroupMember;
public class ProductStoreGroupMemberUpdated implements Event{

	private boolean success;

	public ProductStoreGroupMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
