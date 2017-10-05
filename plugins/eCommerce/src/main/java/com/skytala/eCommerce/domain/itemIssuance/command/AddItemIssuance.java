package com.skytala.eCommerce.domain.itemIssuance.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.itemIssuance.event.ItemIssuanceAdded;
import com.skytala.eCommerce.domain.itemIssuance.mapper.ItemIssuanceMapper;
import com.skytala.eCommerce.domain.itemIssuance.model.ItemIssuance;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddItemIssuance extends Command {

private ItemIssuance elementToBeAdded;
public AddItemIssuance(ItemIssuance elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ItemIssuance addedElement = null;
boolean success = false;
try {
elementToBeAdded.setItemIssuanceId(delegator.getNextSeqId("ItemIssuance"));
GenericValue newValue = delegator.makeValue("ItemIssuance", elementToBeAdded.mapAttributeField());
addedElement = ItemIssuanceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ItemIssuanceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
