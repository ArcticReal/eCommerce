package com.skytala.eCommerce.domain.content.relations.content.event.revision;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.revision.ContentRevision;
public class ContentRevisionFound implements Event{

	private List<ContentRevision> contentRevisions;

	public ContentRevisionFound(List<ContentRevision> contentRevisions) {
		this.contentRevisions = contentRevisions;
	}

	public List<ContentRevision> getContentRevisions()	{
		return contentRevisions;
	}

}
