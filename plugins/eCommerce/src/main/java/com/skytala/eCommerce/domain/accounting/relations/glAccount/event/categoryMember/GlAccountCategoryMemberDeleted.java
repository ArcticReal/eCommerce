package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.categoryMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.categoryMember.GlAccountCategoryMember;
public class GlAccountCategoryMemberDeleted implements Event{

	private boolean success;

	public GlAccountCategoryMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
