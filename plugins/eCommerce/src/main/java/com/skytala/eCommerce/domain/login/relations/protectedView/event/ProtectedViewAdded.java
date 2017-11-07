package com.skytala.eCommerce.domain.login.relations.protectedView.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;
public class ProtectedViewAdded implements Event{

	private ProtectedView addedProtectedView;
	private boolean success;

	public ProtectedViewAdded(ProtectedView addedProtectedView, boolean success){
		this.addedProtectedView = addedProtectedView;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ProtectedView getAddedProtectedView() {
		return addedProtectedView;
	}

}
