package com.skytala.eCommerce.domain.content.relations.contentAssoc.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentAssoc.model.ContentAssoc;
public class ContentAssocFound implements Event{

	private List<ContentAssoc> contentAssocs;

	public ContentAssocFound(List<ContentAssoc> contentAssocs) {
		this.contentAssocs = contentAssocs;
	}

	public List<ContentAssoc> getContentAssocs()	{
		return contentAssocs;
	}

}
