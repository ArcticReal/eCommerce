package com.skytala.eCommerce.domain.shipment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.event.ShipmentAdded;
import com.skytala.eCommerce.domain.shipment.mapper.ShipmentMapper;
import com.skytala.eCommerce.domain.shipment.model.Shipment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipment extends Command {

private Shipment elementToBeAdded;
public AddShipment(Shipment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Shipment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentId(delegator.getNextSeqId("Shipment"));
GenericValue newValue = delegator.makeValue("Shipment", elementToBeAdded.mapAttributeField());
addedElement = ShipmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
