package com.skytala.eCommerce.domain.content.relations.contentSearchResult.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchResult.model.ContentSearchResult;
public class ContentSearchResultFound implements Event{

	private List<ContentSearchResult> contentSearchResults;

	public ContentSearchResultFound(List<ContentSearchResult> contentSearchResults) {
		this.contentSearchResults = contentSearchResults;
	}

	public List<ContentSearchResult> getContentSearchResults()	{
		return contentSearchResults;
	}

}
