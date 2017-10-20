package com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUps;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUps.ShipmentGatewayUpsAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUps.ShipmentGatewayUpsMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUps.ShipmentGatewayUps;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentGatewayUps extends Command {

private ShipmentGatewayUps elementToBeAdded;
public AddShipmentGatewayUps(ShipmentGatewayUps elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentGatewayUps addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentGatewayUps", elementToBeAdded.mapAttributeField());
addedElement = ShipmentGatewayUpsMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentGatewayUpsAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
