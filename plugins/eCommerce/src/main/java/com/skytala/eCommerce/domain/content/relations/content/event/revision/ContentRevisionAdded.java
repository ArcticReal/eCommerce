package com.skytala.eCommerce.domain.content.relations.content.event.revision;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.revision.ContentRevision;
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
