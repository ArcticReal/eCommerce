package com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMech;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.contactMech.ShipmentContactMechMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentContactMech extends Command {

private ShipmentContactMech elementToBeAdded;
public AddShipmentContactMech(ShipmentContactMech elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentContactMech addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentContactMech", elementToBeAdded.mapAttributeField());
addedElement = ShipmentContactMechMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentContactMechAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
