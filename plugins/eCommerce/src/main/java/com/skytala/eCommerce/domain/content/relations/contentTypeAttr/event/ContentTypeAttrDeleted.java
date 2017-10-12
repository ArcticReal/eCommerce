package com.skytala.eCommerce.domain.content.relations.contentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentTypeAttr.model.ContentTypeAttr;
public class ContentTypeAttrDeleted implements Event{

	private boolean success;

	public ContentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
