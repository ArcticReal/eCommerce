package com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.model.ProductStoreGroupMember;
public class ProductStoreGroupMemberDeleted implements Event{

	private boolean success;

	public ProductStoreGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
