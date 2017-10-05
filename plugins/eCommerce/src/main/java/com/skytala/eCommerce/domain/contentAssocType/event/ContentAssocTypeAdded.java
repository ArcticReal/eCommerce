package com.skytala.eCommerce.domain.contentAssocType.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentAssocType.model.ContentAssocType;
public class ContentAssocTypeAdded implements Event{

	private ContentAssocType addedContentAssocType;
	private boolean success;

	public ContentAssocTypeAdded(ContentAssocType addedContentAssocType, boolean success){
		this.addedContentAssocType = addedContentAssocType;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentAssocType getAddedContentAssocType() {
		return addedContentAssocType;
	}

}
