package com.skytala.eCommerce.domain.content.relations.content.event.assocType;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assocType.ContentAssocType;
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
