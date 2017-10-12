package com.skytala.eCommerce.domain.shipment.relations.shipmentItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.event.ShipmentItemAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.mapper.ShipmentItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItem.model.ShipmentItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentItem extends Command {

private ShipmentItem elementToBeAdded;
public AddShipmentItem(ShipmentItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setShipmentItemSeqId(delegator.getNextSeqId("ShipmentItem"));
GenericValue newValue = delegator.makeValue("ShipmentItem", elementToBeAdded.mapAttributeField());
addedElement = ShipmentItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
