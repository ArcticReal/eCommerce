package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.event.OrderSummaryEntryAdded;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.mapper.OrderSummaryEntryMapper;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderSummaryEntry extends Command {

private OrderSummaryEntry elementToBeAdded;
public AddOrderSummaryEntry(OrderSummaryEntry elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderSummaryEntry addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderSummaryEntry", elementToBeAdded.mapAttributeField());
addedElement = OrderSummaryEntryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderSummaryEntryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
