package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.mapper.ShipmentGatewayFedexMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentGatewayFedex extends Command {

private ShipmentGatewayFedex elementToBeAdded;
public AddShipmentGatewayFedex(ShipmentGatewayFedex elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentGatewayFedex addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentGatewayFedex", elementToBeAdded.mapAttributeField());
addedElement = ShipmentGatewayFedexMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentGatewayFedexAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
