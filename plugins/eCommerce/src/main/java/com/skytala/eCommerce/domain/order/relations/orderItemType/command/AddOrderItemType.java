package com.skytala.eCommerce.domain.order.relations.orderItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemType.event.OrderItemTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItemType.mapper.OrderItemTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItemType.model.OrderItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemType extends Command {

private OrderItemType elementToBeAdded;
public AddOrderItemType(OrderItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemTypeId(delegator.getNextSeqId("OrderItemType"));
GenericValue newValue = delegator.makeValue("OrderItemType", elementToBeAdded.mapAttributeField());
addedElement = OrderItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
