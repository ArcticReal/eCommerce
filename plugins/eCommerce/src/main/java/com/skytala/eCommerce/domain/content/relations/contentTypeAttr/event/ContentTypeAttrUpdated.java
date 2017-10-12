package com.skytala.eCommerce.domain.content.relations.contentTypeAttr.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentTypeAttr.model.ContentTypeAttr;
public class ContentTypeAttrUpdated implements Event{

	private boolean success;

	public ContentTypeAttrUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
