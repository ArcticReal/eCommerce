package com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.event.InventoryItemTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.mapper.InventoryItemTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemTypeAttr.model.InventoryItemTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemTypeAttr extends Command {

private InventoryItemTypeAttr elementToBeAdded;
public AddInventoryItemTypeAttr(InventoryItemTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InventoryItemTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
