package com.skytala.eCommerce.domain.content.relations.contentRevisionItem.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.model.ContentRevisionItem;
public class ContentRevisionItemAdded implements Event{

	private ContentRevisionItem addedContentRevisionItem;
	private boolean success;

	public ContentRevisionItemAdded(ContentRevisionItem addedContentRevisionItem, boolean success){
		this.addedContentRevisionItem = addedContentRevisionItem;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentRevisionItem getAddedContentRevisionItem() {
		return addedContentRevisionItem;
	}

}
