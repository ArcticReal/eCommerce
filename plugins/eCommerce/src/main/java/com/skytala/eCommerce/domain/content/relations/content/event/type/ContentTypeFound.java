package com.skytala.eCommerce.domain.content.relations.content.event.type;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.type.ContentType;
public class ContentTypeFound implements Event{

	private List<ContentType> contentTypes;

	public ContentTypeFound(List<ContentType> contentTypes) {
		this.contentTypes = contentTypes;
	}

	public List<ContentType> getContentTypes()	{
		return contentTypes;
	}

}
