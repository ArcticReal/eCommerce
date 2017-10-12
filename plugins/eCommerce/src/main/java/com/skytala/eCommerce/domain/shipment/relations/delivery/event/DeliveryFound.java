package com.skytala.eCommerce.domain.shipment.relations.delivery.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.delivery.model.Delivery;
public class DeliveryFound implements Event{

	private List<Delivery> deliverys;

	public DeliveryFound(List<Delivery> deliverys) {
		this.deliverys = deliverys;
	}

	public List<Delivery> getDeliverys()	{
		return deliverys;
	}

}
