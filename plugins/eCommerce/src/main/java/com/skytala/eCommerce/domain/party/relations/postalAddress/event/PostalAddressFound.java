package com.skytala.eCommerce.domain.party.relations.postalAddress.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
public class PostalAddressFound implements Event{

	private List<PostalAddress> postalAddresss;

	public PostalAddressFound(List<PostalAddress> postalAddresss) {
		this.postalAddresss = postalAddresss;
	}

	public List<PostalAddress> getPostalAddresss()	{
		return postalAddresss;
	}

}
