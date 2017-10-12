package com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.event.ShipmentBoxTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.mapper.ShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentBoxType.model.ShipmentBoxType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentBoxType extends Command {

private ShipmentBoxType elementToBeAdded;
public AddShipmentBoxType(ShipmentBoxType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentBoxType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentBoxTypeId(delegator.getNextSeqId("ShipmentBoxType"));
GenericValue newValue = delegator.makeValue("ShipmentBoxType", elementToBeAdded.mapAttributeField());
addedElement = ShipmentBoxTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentBoxTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
