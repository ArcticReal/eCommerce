package com.skytala.eCommerce.domain.commContentAssocType.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.commContentAssocType.model.CommContentAssocType;
public class CommContentAssocTypeFound implements Event{

	private List<CommContentAssocType> commContentAssocTypes;

	public CommContentAssocTypeFound(List<CommContentAssocType> commContentAssocTypes) {
		this.commContentAssocTypes = commContentAssocTypes;
	}

	public List<CommContentAssocType> getCommContentAssocTypes()	{
		return commContentAssocTypes;
	}

}
