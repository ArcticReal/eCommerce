package com.skytala.eCommerce.domain.content.relations.contentApproval.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentApproval.model.ContentApproval;
public class ContentApprovalDeleted implements Event{

	private boolean success;

	public ContentApprovalDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
