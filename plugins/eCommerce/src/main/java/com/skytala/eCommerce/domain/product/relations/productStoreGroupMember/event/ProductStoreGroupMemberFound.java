package com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.product.relations.productStoreGroupMember.model.ProductStoreGroupMember;
public class ProductStoreGroupMemberFound implements Event{

	private List<ProductStoreGroupMember> productStoreGroupMembers;

	public ProductStoreGroupMemberFound(List<ProductStoreGroupMember> productStoreGroupMembers) {
		this.productStoreGroupMembers = productStoreGroupMembers;
	}

	public List<ProductStoreGroupMember> getProductStoreGroupMembers()	{
		return productStoreGroupMembers;
	}

}
