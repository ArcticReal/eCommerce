package com.skytala.eCommerce.domain.content.relations.content.event.searchResult;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.searchResult.ContentSearchResult;
public class ContentSearchResultAdded implements Event{

	private ContentSearchResult addedContentSearchResult;
	private boolean success;

	public ContentSearchResultAdded(ContentSearchResult addedContentSearchResult, boolean success){
		this.addedContentSearchResult = addedContentSearchResult;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentSearchResult getAddedContentSearchResult() {
		return addedContentSearchResult;
	}

}
