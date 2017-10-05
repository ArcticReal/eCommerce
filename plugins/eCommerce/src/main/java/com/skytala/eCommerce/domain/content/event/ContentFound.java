package com.skytala.eCommerce.domain.content.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.model.Content;
public class ContentFound implements Event{

	private List<Content> contents;

	public ContentFound(List<Content> contents) {
		this.contents = contents;
	}

	public List<Content> getContents()	{
		return contents;
	}

}
