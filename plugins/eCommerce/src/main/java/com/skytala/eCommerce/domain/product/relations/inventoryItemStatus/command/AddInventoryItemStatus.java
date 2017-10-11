package com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.event.InventoryItemStatusAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.mapper.InventoryItemStatusMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemStatus.model.InventoryItemStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemStatus extends Command {

private InventoryItemStatus elementToBeAdded;
public AddInventoryItemStatus(InventoryItemStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InventoryItemStatus", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
