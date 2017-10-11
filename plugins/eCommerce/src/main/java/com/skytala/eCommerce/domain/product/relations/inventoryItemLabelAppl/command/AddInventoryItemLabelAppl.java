package com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.event.InventoryItemLabelApplAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.mapper.InventoryItemLabelApplMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemLabelAppl.model.InventoryItemLabelAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemLabelAppl extends Command {

private InventoryItemLabelAppl elementToBeAdded;
public AddInventoryItemLabelAppl(InventoryItemLabelAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemLabelAppl addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InventoryItemLabelAppl", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemLabelApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemLabelApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
