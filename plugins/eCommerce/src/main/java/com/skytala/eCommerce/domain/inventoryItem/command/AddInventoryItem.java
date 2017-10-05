package com.skytala.eCommerce.domain.inventoryItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.inventoryItem.event.InventoryItemAdded;
import com.skytala.eCommerce.domain.inventoryItem.mapper.InventoryItemMapper;
import com.skytala.eCommerce.domain.inventoryItem.model.InventoryItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItem extends Command {

private InventoryItem elementToBeAdded;
public AddInventoryItem(InventoryItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInventoryItemId(delegator.getNextSeqId("InventoryItem"));
GenericValue newValue = delegator.makeValue("InventoryItem", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
