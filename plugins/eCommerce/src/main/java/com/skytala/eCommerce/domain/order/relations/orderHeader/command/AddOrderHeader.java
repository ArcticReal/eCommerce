package com.skytala.eCommerce.domain.order.relations.orderHeader.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.OrderHeaderAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeader.mapper.OrderHeaderMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderHeader extends Command {

private OrderHeader elementToBeAdded;
public AddOrderHeader(OrderHeader elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderHeader addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderHeader", elementToBeAdded.mapAttributeField());
addedElement = OrderHeaderMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderHeaderAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
