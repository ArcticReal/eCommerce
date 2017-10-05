package com.skytala.eCommerce.domain.orderBlacklistType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderBlacklistType.event.OrderBlacklistTypeAdded;
import com.skytala.eCommerce.domain.orderBlacklistType.mapper.OrderBlacklistTypeMapper;
import com.skytala.eCommerce.domain.orderBlacklistType.model.OrderBlacklistType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderBlacklistType extends Command {

private OrderBlacklistType elementToBeAdded;
public AddOrderBlacklistType(OrderBlacklistType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderBlacklistType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderBlacklistTypeId(delegator.getNextSeqId("OrderBlacklistType"));
GenericValue newValue = delegator.makeValue("OrderBlacklistType", elementToBeAdded.mapAttributeField());
addedElement = OrderBlacklistTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderBlacklistTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
