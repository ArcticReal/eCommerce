package com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.event.InventoryItemDetailAdded;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.mapper.InventoryItemDetailMapper;
import com.skytala.eCommerce.domain.product.relations.inventoryItemDetail.model.InventoryItemDetail;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInventoryItemDetail extends Command {

private InventoryItemDetail elementToBeAdded;
public AddInventoryItemDetail(InventoryItemDetail elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InventoryItemDetail addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInventoryItemDetailSeqId(delegator.getNextSeqId("InventoryItemDetail"));
GenericValue newValue = delegator.makeValue("InventoryItemDetail", elementToBeAdded.mapAttributeField());
addedElement = InventoryItemDetailMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InventoryItemDetailAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
