package com.skytala.eCommerce.domain.content.relations.content.event.approval;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.approval.ContentApproval;
public class ContentApprovalFound implements Event{

	private List<ContentApproval> contentApprovals;

	public ContentApprovalFound(List<ContentApproval> contentApprovals) {
		this.contentApprovals = contentApprovals;
	}

	public List<ContentApproval> getContentApprovals()	{
		return contentApprovals;
	}

}
