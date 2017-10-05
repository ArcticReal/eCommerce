package com.skytala.eCommerce.domain.orderAdjustment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.orderAdjustment.event.OrderAdjustmentAdded;
import com.skytala.eCommerce.domain.orderAdjustment.mapper.OrderAdjustmentMapper;
import com.skytala.eCommerce.domain.orderAdjustment.model.OrderAdjustment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderAdjustment extends Command {

private OrderAdjustment elementToBeAdded;
public AddOrderAdjustment(OrderAdjustment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderAdjustment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setOrderAdjustmentId(delegator.getNextSeqId("OrderAdjustment"));
GenericValue newValue = delegator.makeValue("OrderAdjustment", elementToBeAdded.mapAttributeField());
addedElement = OrderAdjustmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderAdjustmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
