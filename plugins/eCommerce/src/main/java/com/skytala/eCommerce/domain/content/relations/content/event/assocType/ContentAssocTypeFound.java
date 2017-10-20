package com.skytala.eCommerce.domain.content.relations.content.event.assocType;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assocType.ContentAssocType;
public class ContentAssocTypeFound implements Event{

	private List<ContentAssocType> contentAssocTypes;

	public ContentAssocTypeFound(List<ContentAssocType> contentAssocTypes) {
		this.contentAssocTypes = contentAssocTypes;
	}

	public List<ContentAssocType> getContentAssocTypes()	{
		return contentAssocTypes;
	}

}
