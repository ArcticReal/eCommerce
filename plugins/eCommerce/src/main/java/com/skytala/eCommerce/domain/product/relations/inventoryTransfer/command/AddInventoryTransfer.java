package com.skytala.eCommerce.domain.product.relations.inventoryTransfer.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.event.InventoryTransferAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.mapper.InventoryTransferMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryTransfer.model.InventoryTransfer;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryTransfer extends Command {

private InventoryTransfer elementToBeAdded;
public AddInventoryTransfer(InventoryTransfer elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryTransfer addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInventoryTransferId(delegator.getNextSeqId("InventoryTransfer"));
GenericValue newValue = delegator.makeValue("InventoryTransfer", elementToBeAdded.mapAttributeField());
addedElement = InventoryTransferMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryTransferAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
