package com.skytala.eCommerce.domain.content.relations.content.event.keyword;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;
public class ContentKeywordFound implements Event{

	private List<ContentKeyword> contentKeywords;

	public ContentKeywordFound(List<ContentKeyword> contentKeywords) {
		this.contentKeywords = contentKeywords;
	}

	public List<ContentKeyword> getContentKeywords()	{
		return contentKeywords;
	}

}
