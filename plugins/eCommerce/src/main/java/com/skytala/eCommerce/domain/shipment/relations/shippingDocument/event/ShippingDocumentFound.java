package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
public class ShippingDocumentFound implements Event{

	private List<ShippingDocument> shippingDocuments;

	public ShippingDocumentFound(List<ShippingDocument> shippingDocuments) {
		this.shippingDocuments = shippingDocuments;
	}

	public List<ShippingDocument> getShippingDocuments()	{
		return shippingDocuments;
	}

}
