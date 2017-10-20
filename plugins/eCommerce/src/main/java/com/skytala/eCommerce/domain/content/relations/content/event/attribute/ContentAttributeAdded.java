package com.skytala.eCommerce.domain.content.relations.content.event.attribute;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.attribute.ContentAttribute;
public class ContentAttributeAdded implements Event{

	private ContentAttribute addedContentAttribute;
	private boolean success;

	public ContentAttributeAdded(ContentAttribute addedContentAttribute, boolean success){
		this.addedContentAttribute = addedContentAttribute;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentAttribute getAddedContentAttribute() {
		return addedContentAttribute;
	}

}
