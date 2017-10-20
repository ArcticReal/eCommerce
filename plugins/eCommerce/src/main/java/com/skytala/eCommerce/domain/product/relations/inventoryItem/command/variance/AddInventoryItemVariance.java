package com.skytala.eCommerce.domain.product.relations.inventoryItem.command.variance;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.event.variance.InventoryItemVarianceAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper.variance.InventoryItemVarianceMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.variance.InventoryItemVariance;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemVariance extends Command {

private InventoryItemVariance elementToBeAdded;
public AddInventoryItemVariance(InventoryItemVariance elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemVariance addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InventoryItemVariance", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemVarianceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemVarianceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
