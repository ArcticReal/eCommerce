package com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMechType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMechType.ShipmentContactMechTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.contactMechType.ShipmentContactMechTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMechType.ShipmentContactMechType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentContactMechType extends Command {

private ShipmentContactMechType elementToBeAdded;
public AddShipmentContactMechType(ShipmentContactMechType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentContactMechType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentContactMechTypeId(delegator.getNextSeqId("ShipmentContactMechType"));
GenericValue newValue = delegator.makeValue("ShipmentContactMechType", elementToBeAdded.mapAttributeField());
addedElement = ShipmentContactMechTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentContactMechTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
