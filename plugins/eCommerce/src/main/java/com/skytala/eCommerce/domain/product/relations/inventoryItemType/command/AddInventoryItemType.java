package com.skytala.eCommerce.domain.product.relations.inventoryItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemType.event.InventoryItemTypeAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemType.mapper.InventoryItemTypeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemType.model.InventoryItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemType extends Command {

private InventoryItemType elementToBeAdded;
public AddInventoryItemType(InventoryItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInventoryItemTypeId(delegator.getNextSeqId("InventoryItemType"));
GenericValue newValue = delegator.makeValue("InventoryItemType", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
