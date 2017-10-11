package com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.event.OrderHeaderWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.mapper.OrderHeaderWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeaderWorkEffort.model.OrderHeaderWorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderHeaderWorkEffort extends Command {

private OrderHeaderWorkEffort elementToBeAdded;
public AddOrderHeaderWorkEffort(OrderHeaderWorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderHeaderWorkEffort addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderHeaderWorkEffort", elementToBeAdded.mapAttributeField());
addedElement = OrderHeaderWorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderHeaderWorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
