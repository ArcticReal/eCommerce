package com.skytala.eCommerce.domain.product.relations.physicalInventory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.event.PhysicalInventoryAdded;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.mapper.PhysicalInventoryMapper;
import com.skytala.eCommerce.domain.product.relations.physicalInventory.model.PhysicalInventory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPhysicalInventory extends Command {

private PhysicalInventory elementToBeAdded;
public AddPhysicalInventory(PhysicalInventory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PhysicalInventory addedElement = null;
boolean success = false;
try {
elementToBeAdded.setPhysicalInventoryId(delegator.getNextSeqId("PhysicalInventory"));
GenericValue newValue = delegator.makeValue("PhysicalInventory", elementToBeAdded.mapAttributeField());
addedElement = PhysicalInventoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PhysicalInventoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
