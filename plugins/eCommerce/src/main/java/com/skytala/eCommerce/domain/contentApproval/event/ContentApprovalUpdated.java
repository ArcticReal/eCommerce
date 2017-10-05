package com.skytala.eCommerce.domain.contentApproval.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentApproval.model.ContentApproval;
public class ContentApprovalUpdated implements Event{

	private boolean success;

	public ContentApprovalUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
