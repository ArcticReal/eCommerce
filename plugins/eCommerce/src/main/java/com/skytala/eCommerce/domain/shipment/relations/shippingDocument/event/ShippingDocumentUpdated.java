package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
public class ShippingDocumentUpdated implements Event{

	private boolean success;

	public ShippingDocumentUpdated(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
