package com.skytala.eCommerce.domain.content.relations.contentAttribute.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAttribute.model.ContentAttribute;
public class ContentAttributeFound implements Event{

	private List<ContentAttribute> contentAttributes;

	public ContentAttributeFound(List<ContentAttribute> contentAttributes) {
		this.contentAttributes = contentAttributes;
	}

	public List<ContentAttribute> getContentAttributes()	{
		return contentAttributes;
	}

}
