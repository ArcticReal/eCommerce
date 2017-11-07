package com.skytala.eCommerce.domain.login.relations.protectedView.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;
public class ProtectedViewFound implements Event{

	private List<ProtectedView> protectedViews;

	public ProtectedViewFound(List<ProtectedView> protectedViews) {
		this.protectedViews = protectedViews;
	}

	public List<ProtectedView> getProtectedViews()	{
		return protectedViews;
	}

}
