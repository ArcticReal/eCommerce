package com.skytala.eCommerce.domain.content.relations.content.event.searchResult;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.searchResult.ContentSearchResult;
public class ContentSearchResultUpdated implements Event{

	private boolean success;

	public ContentSearchResultUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
