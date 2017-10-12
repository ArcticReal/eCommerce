package com.skytala.eCommerce.domain.content.relations.contentSearchResult.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchResult.model.ContentSearchResult;
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
