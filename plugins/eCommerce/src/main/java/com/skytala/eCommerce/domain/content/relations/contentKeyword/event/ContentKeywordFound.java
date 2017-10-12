package com.skytala.eCommerce.domain.content.relations.contentKeyword.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentKeyword.model.ContentKeyword;
public class ContentKeywordFound implements Event{

	private List<ContentKeyword> contentKeywords;

	public ContentKeywordFound(List<ContentKeyword> contentKeywords) {
		this.contentKeywords = contentKeywords;
	}

	public List<ContentKeyword> getContentKeywords()	{
		return contentKeywords;
	}

}
