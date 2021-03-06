package com.skytala.eCommerce.domain.shipment.relations.picklist.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.picklist.event.item.PicklistItemAdded;
import com.skytala.eCommerce.domain.shipment.relations.picklist.mapper.item.PicklistItemMapper;
import com.skytala.eCommerce.domain.shipment.relations.picklist.model.item.PicklistItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPicklistItem extends Command {

private PicklistItem elementToBeAdded;
public AddPicklistItem(PicklistItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PicklistItem addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PicklistItem", elementToBeAdded.mapAttributeField());
addedElement = PicklistItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PicklistItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
