package com.skytala.eCommerce.domain.content.relations.contentAssoc.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAssoc.model.ContentAssoc;
public class ContentAssocAdded implements Event{

	private ContentAssoc addedContentAssoc;
	private boolean success;

	public ContentAssocAdded(ContentAssoc addedContentAssoc, boolean success){
		this.addedContentAssoc = addedContentAssoc;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentAssoc getAddedContentAssoc() {
		return addedContentAssoc;
	}

}
