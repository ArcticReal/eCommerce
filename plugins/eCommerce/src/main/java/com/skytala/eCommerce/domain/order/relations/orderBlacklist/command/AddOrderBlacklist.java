package com.skytala.eCommerce.domain.order.relations.orderBlacklist.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.event.OrderBlacklistAdded;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.mapper.OrderBlacklistMapper;
import com.skytala.eCommerce.domain.order.relations.orderBlacklist.model.OrderBlacklist;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderBlacklist extends Command {

private OrderBlacklist elementToBeAdded;
public AddOrderBlacklist(OrderBlacklist elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderBlacklist addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBlacklistString(delegator.getNextSeqId("OrderBlacklist"));
GenericValue newValue = delegator.makeValue("OrderBlacklist", elementToBeAdded.mapAttributeField());
addedElement = OrderBlacklistMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderBlacklistAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
