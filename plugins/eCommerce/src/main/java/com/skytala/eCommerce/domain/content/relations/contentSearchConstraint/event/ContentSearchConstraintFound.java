package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;
public class ContentSearchConstraintFound implements Event{

	private List<ContentSearchConstraint> contentSearchConstraints;

	public ContentSearchConstraintFound(List<ContentSearchConstraint> contentSearchConstraints) {
		this.contentSearchConstraints = contentSearchConstraints;
	}

	public List<ContentSearchConstraint> getContentSearchConstraints()	{
		return contentSearchConstraints;
	}

}
