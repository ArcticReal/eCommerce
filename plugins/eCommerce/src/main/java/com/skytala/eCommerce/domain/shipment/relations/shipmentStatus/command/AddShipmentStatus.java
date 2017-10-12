package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.event.ShipmentStatusAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.mapper.ShipmentStatusMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentStatus extends Command {

private ShipmentStatus elementToBeAdded;
public AddShipmentStatus(ShipmentStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentStatus", elementToBeAdded.mapAttributeField());
addedElement = ShipmentStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
