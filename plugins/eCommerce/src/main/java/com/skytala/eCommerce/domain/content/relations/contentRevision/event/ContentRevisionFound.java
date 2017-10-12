package com.skytala.eCommerce.domain.content.relations.contentRevision.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentRevision.model.ContentRevision;
public class ContentRevisionFound implements Event{

	private List<ContentRevision> contentRevisions;

	public ContentRevisionFound(List<ContentRevision> contentRevisions) {
		this.contentRevisions = contentRevisions;
	}

	public List<ContentRevision> getContentRevisions()	{
		return contentRevisions;
	}

}
