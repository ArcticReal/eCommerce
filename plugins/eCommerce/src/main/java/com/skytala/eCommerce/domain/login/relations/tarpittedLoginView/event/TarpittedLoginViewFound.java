package com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.login.relations.tarpittedLoginView.model.TarpittedLoginView;
public class TarpittedLoginViewFound implements Event{

	private List<TarpittedLoginView> tarpittedLoginViews;

	public TarpittedLoginViewFound(List<TarpittedLoginView> tarpittedLoginViews) {
		this.tarpittedLoginViews = tarpittedLoginViews;
	}

	public List<TarpittedLoginView> getTarpittedLoginViews()	{
		return tarpittedLoginViews;
	}

}
