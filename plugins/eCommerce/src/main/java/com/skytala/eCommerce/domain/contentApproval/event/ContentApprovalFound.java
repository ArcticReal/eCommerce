package com.skytala.eCommerce.domain.contentApproval.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentApproval.model.ContentApproval;
public class ContentApprovalFound implements Event{

	private List<ContentApproval> contentApprovals;

	public ContentApprovalFound(List<ContentApproval> contentApprovals) {
		this.contentApprovals = contentApprovals;
	}

	public List<ContentApproval> getContentApprovals()	{
		return contentApprovals;
	}

}
