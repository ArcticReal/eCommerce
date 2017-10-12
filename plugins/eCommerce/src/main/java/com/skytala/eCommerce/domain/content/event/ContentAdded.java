package com.skytala.eCommerce.domain.content.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.model.Content;
public class ContentAdded implements Event{

	private Content addedContent;
	private boolean success;

	public ContentAdded(Content addedContent, boolean success){
		this.addedContent = addedContent;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public Content getAddedContent() {
		return addedContent;
	}

}
