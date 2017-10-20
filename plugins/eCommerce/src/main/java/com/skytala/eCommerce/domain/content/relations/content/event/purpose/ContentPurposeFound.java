package com.skytala.eCommerce.domain.content.relations.content.event.purpose;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.purpose.ContentPurpose;
public class ContentPurposeFound implements Event{

	private List<ContentPurpose> contentPurposes;

	public ContentPurposeFound(List<ContentPurpose> contentPurposes) {
		this.contentPurposes = contentPurposes;
	}

	public List<ContentPurpose> getContentPurposes()	{
		return contentPurposes;
	}

}
