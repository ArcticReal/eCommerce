package com.skytala.eCommerce.domain.content.relations.content.event.approval;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.approval.ContentApproval;
public class ContentApprovalDeleted implements Event{

	private boolean success;

	public ContentApprovalDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
