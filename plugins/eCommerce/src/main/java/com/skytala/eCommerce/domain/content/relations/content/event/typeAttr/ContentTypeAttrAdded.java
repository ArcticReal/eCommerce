package com.skytala.eCommerce.domain.content.relations.content.event.typeAttr;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.typeAttr.ContentTypeAttr;
public class ContentTypeAttrAdded implements Event{

	private ContentTypeAttr addedContentTypeAttr;
	private boolean success;

	public ContentTypeAttrAdded(ContentTypeAttr addedContentTypeAttr, boolean success){
		this.addedContentTypeAttr = addedContentTypeAttr;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentTypeAttr getAddedContentTypeAttr() {
		return addedContentTypeAttr;
	}

}
