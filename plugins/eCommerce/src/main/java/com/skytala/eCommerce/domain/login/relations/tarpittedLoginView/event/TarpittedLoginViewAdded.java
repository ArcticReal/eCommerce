package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;
public class TarpittedLoginViewAdded implements Event{

	private TarpittedLoginView addedTarpittedLoginView;
	private boolean success;

	public TarpittedLoginViewAdded(TarpittedLoginView addedTarpittedLoginView, boolean success){
		this.addedTarpittedLoginView = addedTarpittedLoginView;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public TarpittedLoginView getAddedTarpittedLoginView() {
		return addedTarpittedLoginView;
	}

}
