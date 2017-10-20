package com.skytala.eCommerce.domain.content.relations.content.event.approval;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.approval.ContentApproval;
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
