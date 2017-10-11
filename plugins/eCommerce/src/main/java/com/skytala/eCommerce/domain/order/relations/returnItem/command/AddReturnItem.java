package com.skytala.eCommerce.domain.order.relations.returnItem.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.ReturnItemAdded;
import com.skytala.eCommerce.domain.order.relations.returnItem.mapper.ReturnItemMapper;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.ReturnItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddReturnItem extends Command {

private ReturnItem elementToBeAdded;
public AddReturnItem(ReturnItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ReturnItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setReturnItemSeqId(delegator.getNextSeqId("ReturnItem"));
GenericValue newValue = delegator.makeValue("ReturnItem", elementToBeAdded.mapAttributeField());
addedElement = ReturnItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ReturnItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
