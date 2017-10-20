package com.skytala.eCommerce.domain.accounting.relations.glAccount.event.groupMember;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.groupMember.GlAccountGroupMember;
public class GlAccountGroupMemberUpdated implements Event{

	private boolean success;

	public GlAccountGroupMemberUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
