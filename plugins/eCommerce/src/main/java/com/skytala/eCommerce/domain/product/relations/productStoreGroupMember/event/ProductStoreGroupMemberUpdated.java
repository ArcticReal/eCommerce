package com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.model.ProductStoreGroupMember;
public class ProductStoreGroupMemberUpdated implements Event{

	private boolean success;

	public ProductStoreGroupMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
