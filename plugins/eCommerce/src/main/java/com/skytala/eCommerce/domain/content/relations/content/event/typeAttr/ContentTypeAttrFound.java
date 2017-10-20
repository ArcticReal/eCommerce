package com.skytala.eCommerce.domain.content.relations.content.event.typeAttr;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.typeAttr.ContentTypeAttr;
public class ContentTypeAttrFound implements Event{

	private List<ContentTypeAttr> contentTypeAttrs;

	public ContentTypeAttrFound(List<ContentTypeAttr> contentTypeAttrs) {
		this.contentTypeAttrs = contentTypeAttrs;
	}

	public List<ContentTypeAttr> getContentTypeAttrs()	{
		return contentTypeAttrs;
	}

}
