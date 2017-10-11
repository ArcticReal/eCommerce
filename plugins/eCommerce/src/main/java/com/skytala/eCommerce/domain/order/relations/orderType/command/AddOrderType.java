package com.skytala.eCommerce.domain.order.relations.orderType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderType.event.OrderTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderType.mapper.OrderTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderType.model.OrderType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderType extends Command {

private OrderType elementToBeAdded;
public AddOrderType(OrderType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderTypeId(delegator.getNextSeqId("OrderType"));
GenericValue newValue = delegator.makeValue("OrderType", elementToBeAdded.mapAttributeField());
addedElement = OrderTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
