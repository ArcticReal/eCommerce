package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember.GlAccountGroupMember;
public class GlAccountGroupMemberDeleted implements Event{

	private boolean success;

	public GlAccountGroupMemberDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
