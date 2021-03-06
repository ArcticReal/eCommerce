package com.skytala.eCommerce.domain.content.relations.content.event.purposeOperation;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purposeOperation.ContentPurposeOperation;
public class ContentPurposeOperationAdded implements Event{

	private ContentPurposeOperation addedContentPurposeOperation;
	private boolean success;

	public ContentPurposeOperationAdded(ContentPurposeOperation addedContentPurposeOperation, boolean success){
		this.addedContentPurposeOperation = addedContentPurposeOperation;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentPurposeOperation getAddedContentPurposeOperation() {
		return addedContentPurposeOperation;
	}

}
