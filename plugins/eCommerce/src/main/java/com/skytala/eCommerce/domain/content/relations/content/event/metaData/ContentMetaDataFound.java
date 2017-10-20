package com.skytala.eCommerce.domain.content.relations.content.event.metaData;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.metaData.ContentMetaData;
public class ContentMetaDataFound implements Event{

	private List<ContentMetaData> contentMetaDatas;

	public ContentMetaDataFound(List<ContentMetaData> contentMetaDatas) {
		this.contentMetaDatas = contentMetaDatas;
	}

	public List<ContentMetaData> getContentMetaDatas()	{
		return contentMetaDatas;
	}

}
