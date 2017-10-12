package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.model.GlAccountCategoryMember;
public class GlAccountCategoryMemberDeleted implements Event{

	private boolean success;

	public GlAccountCategoryMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
