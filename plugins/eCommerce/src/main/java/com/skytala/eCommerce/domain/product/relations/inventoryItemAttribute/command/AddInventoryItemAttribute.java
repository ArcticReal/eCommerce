package com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.event.InventoryItemAttributeAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.mapper.InventoryItemAttributeMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemAttribute.model.InventoryItemAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemAttribute extends Command {

private InventoryItemAttribute elementToBeAdded;
public AddInventoryItemAttribute(InventoryItemAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InventoryItemAttribute", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
