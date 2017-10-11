package com.skytala.eCommerce.domain.order.relations.orderTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderTerm.event.OrderTermAdded;
import com.skytala.eCommerce.domain.order.relations.orderTerm.mapper.OrderTermMapper;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderTerm extends Command {

private OrderTerm elementToBeAdded;
public AddOrderTerm(OrderTerm elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderTerm addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemSeqId(delegator.getNextSeqId("OrderTerm"));
GenericValue newValue = delegator.makeValue("OrderTerm", elementToBeAdded.mapAttributeField());
addedElement = OrderTermMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderTermAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
