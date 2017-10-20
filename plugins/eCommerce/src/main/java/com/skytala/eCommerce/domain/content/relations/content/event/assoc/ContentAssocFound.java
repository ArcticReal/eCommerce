package com.skytala.eCommerce.domain.content.relations.content.event.assoc;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.assoc.ContentAssoc;
public class ContentAssocFound implements Event{

	private List<ContentAssoc> contentAssocs;

	public ContentAssocFound(List<ContentAssoc> contentAssocs) {
		this.contentAssocs = contentAssocs;
	}

	public List<ContentAssoc> getContentAssocs()	{
		return contentAssocs;
	}

}
