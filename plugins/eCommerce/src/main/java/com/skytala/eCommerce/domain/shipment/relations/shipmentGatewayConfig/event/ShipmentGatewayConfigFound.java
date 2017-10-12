package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.event;

import java.util.List;

import com.skytala.eCommerce.framework.pubsub.Event;

import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfig.model.ShipmentGatewayConfig;
public class ShipmentGatewayConfigFound implements Event{

	private List<ShipmentGatewayConfig> shipmentGatewayConfigs;

	public ShipmentGatewayConfigFound(List<ShipmentGatewayConfig> shipmentGatewayConfigs) {
		this.shipmentGatewayConfigs = shipmentGatewayConfigs;
	}

	public List<ShipmentGatewayConfig> getShipmentGatewayConfigs()	{
		return shipmentGatewayConfigs;
	}

}
