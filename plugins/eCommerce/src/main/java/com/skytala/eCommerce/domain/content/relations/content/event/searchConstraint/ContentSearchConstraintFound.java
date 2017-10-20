package com.skytala.eCommerce.domain.content.relations.content.event.searchConstraint;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.content.model.searchConstraint.ContentSearchConstraint;
public class ContentSearchConstraintFound implements Event{

	private List<ContentSearchConstraint> contentSearchConstraints;

	public ContentSearchConstraintFound(List<ContentSearchConstraint> contentSearchConstraints) {
		this.contentSearchConstraints = contentSearchConstraints;
	}

	public List<ContentSearchConstraint> getContentSearchConstraints()	{
		return contentSearchConstraints;
	}

}
