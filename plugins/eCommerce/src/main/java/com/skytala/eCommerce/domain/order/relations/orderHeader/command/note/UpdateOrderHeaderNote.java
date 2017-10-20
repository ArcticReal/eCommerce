package com.skytala.eCommerce.domain.order.relations.orderHeader.command.note;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.orderHeader.event.note.OrderHeaderNoteUpdated;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.note.OrderHeaderNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateOrderHeaderNote extends Command {

private OrderHeaderNote elementToBeUpdated;

public UpdateOrderHeaderNote(OrderHeaderNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public OrderHeaderNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(OrderHeaderNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("OrderHeaderNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(OrderHeaderNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(OrderHeaderNote.class);
}
success = false;
}
Event resultingEvent = new OrderHeaderNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
