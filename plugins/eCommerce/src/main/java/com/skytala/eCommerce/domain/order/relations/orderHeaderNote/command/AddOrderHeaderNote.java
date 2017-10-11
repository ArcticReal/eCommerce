package com.skytala.eCommerce.domain.order.relations.orderHeaderNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.event.OrderHeaderNoteAdded;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.mapper.OrderHeaderNoteMapper;
import com.skytala.eCommerce.domain.order.relations.orderHeaderNote.model.OrderHeaderNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddOrderHeaderNote extends Command {

private OrderHeaderNote elementToBeAdded;
public AddOrderHeaderNote(OrderHeaderNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

OrderHeaderNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("OrderHeaderNote", elementToBeAdded.mapAttributeField());
addedElement = OrderHeaderNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new OrderHeaderNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
