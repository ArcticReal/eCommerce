package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.event.ShipmentGatewayConfigTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.mapper.ShipmentGatewayConfigTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayConfigType.model.ShipmentGatewayConfigType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentGatewayConfigType extends Command {

private ShipmentGatewayConfigType elementToBeAdded;
public AddShipmentGatewayConfigType(ShipmentGatewayConfigType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentGatewayConfigType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentGatewayConfTypeId(delegator.getNextSeqId("ShipmentGatewayConfigType"));
GenericValue newValue = delegator.makeValue("ShipmentGatewayConfigType", elementToBeAdded.mapAttributeField());
addedElement = ShipmentGatewayConfigTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentGatewayConfigTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
