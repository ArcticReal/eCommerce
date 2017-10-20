package com.skytala.eCommerce.domain.shipment.relations.shipment.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.attribute.ShipmentAttributeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.attribute.ShipmentAttributeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.attribute.ShipmentAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentAttribute extends Command {

private ShipmentAttribute elementToBeAdded;
public AddShipmentAttribute(ShipmentAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentAttribute", elementToBeAdded.mapAttributeField());
addedElement = ShipmentAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
