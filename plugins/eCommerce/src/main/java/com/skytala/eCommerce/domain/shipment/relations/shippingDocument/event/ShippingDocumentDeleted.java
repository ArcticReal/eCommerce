package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
public class ShippingDocumentDeleted implements Event{

	private boolean success;

	public ShippingDocumentDeleted(boolean success) {
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

}
