package com.skytala.eCommerce.domain.party.relations.postalAddress.event.boundary;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.party.relations.postalAddress.model.boundary.PostalAddressBoundary;
public class PostalAddressBoundaryFound implements Event{

	private List<PostalAddressBoundary> postalAddressBoundarys;

	public PostalAddressBoundaryFound(List<PostalAddressBoundary> postalAddressBoundarys) {
		this.postalAddressBoundarys = postalAddressBoundarys;
	}

	public List<PostalAddressBoundary> getPostalAddressBoundarys()	{
		return postalAddressBoundarys;
	}

}
