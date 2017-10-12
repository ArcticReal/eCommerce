package com.skytala.eCommerce.domain.content.relations.contentApproval.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentApproval.model.ContentApproval;
public class ContentApprovalUpdated implements Event{

	private boolean success;

	public ContentApprovalUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
