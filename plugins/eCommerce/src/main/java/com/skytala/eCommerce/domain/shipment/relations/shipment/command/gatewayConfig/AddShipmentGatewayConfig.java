package com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfig;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfig.ShipmentGatewayConfigAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayConfig.ShipmentGatewayConfigMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfig.ShipmentGatewayConfig;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentGatewayConfig extends Command {

private ShipmentGatewayConfig elementToBeAdded;
public AddShipmentGatewayConfig(ShipmentGatewayConfig elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentGatewayConfig addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentGatewayConfigId(delegator.getNextSeqId("ShipmentGatewayConfig"));
GenericValue newValue = delegator.makeValue("ShipmentGatewayConfig", elementToBeAdded.mapAttributeField());
addedElement = ShipmentGatewayConfigMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentGatewayConfigAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
