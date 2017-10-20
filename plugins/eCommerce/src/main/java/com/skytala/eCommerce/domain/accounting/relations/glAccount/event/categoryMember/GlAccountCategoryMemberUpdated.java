package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryMember.GlAccountCategoryMember;
public class GlAccountCategoryMemberUpdated implements Event{

	private boolean success;

	public GlAccountCategoryMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
