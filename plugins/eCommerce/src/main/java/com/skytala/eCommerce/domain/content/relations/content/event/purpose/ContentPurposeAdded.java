package com.skytala.eCommerce.domain.content.relations.content.event.purpose;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;
public class ContentPurposeAdded implements Event{

	private ContentPurpose addedContentPurpose;
	private boolean success;

	public ContentPurposeAdded(ContentPurpose addedContentPurpose, boolean success){
		this.addedContentPurpose = addedContentPurpose;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentPurpose getAddedContentPurpose() {
		return addedContentPurpose;
	}

}
