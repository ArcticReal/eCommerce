package com.skytala.eCommerce.domain.content.relations.contentRevisionItem.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.model.ContentRevisionItem;
public class ContentRevisionItemFound implements Event{

	private List<ContentRevisionItem> contentRevisionItems;

	public ContentRevisionItemFound(List<ContentRevisionItem> contentRevisionItems) {
		this.contentRevisionItems = contentRevisionItems;
	}

	public List<ContentRevisionItem> getContentRevisionItems()	{
		return contentRevisionItems;
	}

}
