package com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayUsps;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayUsps.ShipmentGatewayUspsAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.gatewayUsps.ShipmentGatewayUspsMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayUsps.ShipmentGatewayUsps;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentGatewayUsps extends Command {

private ShipmentGatewayUsps elementToBeAdded;
public AddShipmentGatewayUsps(ShipmentGatewayUsps elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentGatewayUsps addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentGatewayUsps", elementToBeAdded.mapAttributeField());
addedElement = ShipmentGatewayUspsMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentGatewayUspsAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
