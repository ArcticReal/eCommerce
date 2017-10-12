package com.skytala.eCommerce.domain.content.relations.contentApproval.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentApproval.model.ContentApproval;
public class ContentApprovalAdded implements Event{

	private ContentApproval addedContentApproval;
	private boolean success;

	public ContentApprovalAdded(ContentApproval addedContentApproval, boolean success){
		this.addedContentApproval = addedContentApproval;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentApproval getAddedContentApproval() {
		return addedContentApproval;
	}

}
