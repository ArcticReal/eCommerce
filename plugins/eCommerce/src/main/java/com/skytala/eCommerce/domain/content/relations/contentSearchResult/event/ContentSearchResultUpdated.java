package com.skytala.eCommerce.domain.content.relations.contentSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchResult.model.ContentSearchResult;
public class ContentSearchResultUpdated implements Event{

	private boolean success;

	public ContentSearchResultUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
