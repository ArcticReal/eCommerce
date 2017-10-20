package com.skytala.eCommerce.domain.order.relations.orderItem.command.assocType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.event.assocType.OrderItemAssocTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderItem.mapper.assocType.OrderItemAssocTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.assocType.OrderItemAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderItemAssocType extends Command {

private OrderItemAssocType elementToBeAdded;
public AddOrderItemAssocType(OrderItemAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderItemAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderItemAssocTypeId(delegator.getNextSeqId("OrderItemAssocType"));
GenericValue newValue = delegator.makeValue("OrderItemAssocType", elementToBeAdded.mapAttributeField());
addedElement = OrderItemAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderItemAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
