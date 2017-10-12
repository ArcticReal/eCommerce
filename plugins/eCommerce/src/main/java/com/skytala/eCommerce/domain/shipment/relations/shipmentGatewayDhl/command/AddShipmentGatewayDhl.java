package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.event.ShipmentGatewayDhlAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.mapper.ShipmentGatewayDhlMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayDhl.model.ShipmentGatewayDhl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentGatewayDhl extends Command {

private ShipmentGatewayDhl elementToBeAdded;
public AddShipmentGatewayDhl(ShipmentGatewayDhl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentGatewayDhl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentGatewayDhl", elementToBeAdded.mapAttributeField());
addedElement = ShipmentGatewayDhlMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentGatewayDhlAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
