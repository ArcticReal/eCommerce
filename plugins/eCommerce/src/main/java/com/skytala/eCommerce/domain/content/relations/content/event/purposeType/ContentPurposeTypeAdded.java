package com.skytala.eCommerce.domain.content.relations.content.event.purposeType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purposeType.ContentPurposeType;
public class ContentPurposeTypeAdded implements Event{

	private ContentPurposeType addedContentPurposeType;
	private boolean success;

	public ContentPurposeTypeAdded(ContentPurposeType addedContentPurposeType, boolean success){
		this.addedContentPurposeType = addedContentPurposeType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentPurposeType getAddedContentPurposeType() {
		return addedContentPurposeType;
	}

}
