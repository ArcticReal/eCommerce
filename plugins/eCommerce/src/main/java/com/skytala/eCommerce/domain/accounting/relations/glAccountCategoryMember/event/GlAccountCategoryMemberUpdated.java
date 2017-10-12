package com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccountCategoryMember.model.GlAccountCategoryMember;
public class GlAccountCategoryMemberUpdated implements Event{

	private boolean success;

	public GlAccountCategoryMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
