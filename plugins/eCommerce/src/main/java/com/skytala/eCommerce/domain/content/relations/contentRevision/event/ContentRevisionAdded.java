package com.skytala.eCommerce.domain.content.relations.contentRevision.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRevision.model.ContentRevision;
public class ContentRevisionAdded implements Event{

	private ContentRevision addedContentRevision;
	private boolean success;

	public ContentRevisionAdded(ContentRevision addedContentRevision, boolean success){
		this.addedContentRevision = addedContentRevision;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentRevision getAddedContentRevision() {
		return addedContentRevision;
	}

}
