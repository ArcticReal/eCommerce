package com.skytala.eCommerce.domain.order.relations.orderContent.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderContent.event.type.OrderContentTypeAdded;
import com.skytala.eCommerce.domain.order.relations.orderContent.mapper.type.OrderContentTypeMapper;
import com.skytala.eCommerce.domain.order.relations.orderContent.model.type.OrderContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderContentType extends Command {

private OrderContentType elementToBeAdded;
public AddOrderContentType(OrderContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderContentTypeId(delegator.getNextSeqId("OrderContentType"));
GenericValue newValue = delegator.makeValue("OrderContentType", elementToBeAdded.mapAttributeField());
addedElement = OrderContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
