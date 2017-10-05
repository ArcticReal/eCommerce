package com.skytala.eCommerce.domain.contentType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.contentType.model.ContentType;
public class ContentTypeFound implements Event{

	private List<ContentType> contentTypes;

	public ContentTypeFound(List<ContentType> contentTypes) {
		this.contentTypes = contentTypes;
	}

	public List<ContentType> getContentTypes()	{
		return contentTypes;
	}

}
