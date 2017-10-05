package com.skytala.eCommerce.domain.shipmentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipmentType.event.ShipmentTypeAdded;
import com.skytala.eCommerce.domain.shipmentType.mapper.ShipmentTypeMapper;
import com.skytala.eCommerce.domain.shipmentType.model.ShipmentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentType extends Command {

private ShipmentType elementToBeAdded;
public AddShipmentType(ShipmentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentTypeId(delegator.getNextSeqId("ShipmentType"));
GenericValue newValue = delegator.makeValue("ShipmentType", elementToBeAdded.mapAttributeField());
addedElement = ShipmentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
