package com.skytala.eCommerce.domain.content.relations.content.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.typeAttr.ContentTypeAttr;
public class ContentTypeAttrDeleted implements Event{

	private boolean success;

	public ContentTypeAttrDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
