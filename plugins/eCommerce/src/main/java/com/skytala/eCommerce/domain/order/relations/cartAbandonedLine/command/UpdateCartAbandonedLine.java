package com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.event.CartAbandonedLineUpdated;
import com.skytala.eCommerce.domain.order.relations.cartAbandonedLine.model.CartAbandonedLine;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCartAbandonedLine extends Command {

private CartAbandonedLine elementToBeUpdated;

public UpdateCartAbandonedLine(CartAbandonedLine elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CartAbandonedLine getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CartAbandonedLine elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CartAbandonedLine", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CartAbandonedLine.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CartAbandonedLine.class);
}
success = false;
}
Event resultingEvent = new CartAbandonedLineUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
