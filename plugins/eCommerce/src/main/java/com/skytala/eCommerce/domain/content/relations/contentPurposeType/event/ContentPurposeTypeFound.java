package com.skytala.eCommerce.domain.content.relations.contentPurposeType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentPurposeType.model.ContentPurposeType;
public class ContentPurposeTypeFound implements Event{

	private List<ContentPurposeType> contentPurposeTypes;

	public ContentPurposeTypeFound(List<ContentPurposeType> contentPurposeTypes) {
		this.contentPurposeTypes = contentPurposeTypes;
	}

	public List<ContentPurposeType> getContentPurposeTypes()	{
		return contentPurposeTypes;
	}

}
