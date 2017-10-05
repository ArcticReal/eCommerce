package com.skytala.eCommerce.domain.orderItemChange.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderItemChange.event.OrderItemChangeAdded;
import com.skytala.eCommerce.domain.orderItemChange.mapper.OrderItemChangeMapper;
import com.skytala.eCommerce.domain.orderItemChange.model.OrderItemChange;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemChange extends Command {

private OrderItemChange elementToBeAdded;
public AddOrderItemChange(OrderItemChange elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemChange addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemChangeId(delegator.getNextSeqId("OrderItemChange"));
GenericValue newValue = delegator.makeValue("OrderItemChange", elementToBeAdded.mapAttributeField());
addedElement = OrderItemChangeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemChangeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
