package com.skytala.eCommerce.domain.product.relations.inventoryItem.command.tempRes;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.tempRes.InventoryItemTempResAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.tempRes.InventoryItemTempResMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.tempRes.InventoryItemTempRes;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemTempRes extends Command {

private InventoryItemTempRes elementToBeAdded;
public AddInventoryItemTempRes(InventoryItemTempRes elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemTempRes addedElement = null;
boolean success = false;
try {
elementToBeAdded.setVisitId(delegator.getNextSeqId("InventoryItemTempRes"));
GenericValue newValue = delegator.makeValue("InventoryItemTempRes", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemTempResMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemTempResAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
