package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
public class ShippingDocumentAdded implements Event{

	private ShippingDocument addedShippingDocument;
	private boolean success;

	public ShippingDocumentAdded(ShippingDocument addedShippingDocument, boolean success){
		this.addedShippingDocument = addedShippingDocument;
		this.success = success;
	}

	public boolean isSuccess()	{
		return success;
	}

	public ShippingDocument getAddedShippingDocument() {
		return addedShippingDocument;
	}

}
