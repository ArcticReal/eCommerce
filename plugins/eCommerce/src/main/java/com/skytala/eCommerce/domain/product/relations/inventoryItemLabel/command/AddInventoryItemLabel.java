package com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.event.InventoryItemLabelAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.mapper.InventoryItemLabelMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabel.model.InventoryItemLabel;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemLabel extends Command {

private InventoryItemLabel elementToBeAdded;
public AddInventoryItemLabel(InventoryItemLabel elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemLabel addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInventoryItemLabelId(delegator.getNextSeqId("InventoryItemLabel"));
GenericValue newValue = delegator.makeValue("InventoryItemLabel", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemLabelMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemLabelAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
