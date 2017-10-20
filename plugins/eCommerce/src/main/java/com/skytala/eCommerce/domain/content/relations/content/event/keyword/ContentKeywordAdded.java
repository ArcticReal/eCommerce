package com.skytala.eCommerce.domain.content.relations.content.event.keyword;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;
public class ContentKeywordAdded implements Event{

	private ContentKeyword addedContentKeyword;
	private boolean success;

	public ContentKeywordAdded(ContentKeyword addedContentKeyword, boolean success){
		this.addedContentKeyword = addedContentKeyword;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ContentKeyword getAddedContentKeyword() {
		return addedContentKeyword;
	}

}
