package com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.event.SubscriptionCommEventUpdated;
import com.skytala.eCommerce.domain.product.relations.subscriptionCommEvent.model.SubscriptionCommEvent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSubscriptionCommEvent extends Command {

private SubscriptionCommEvent elementToBeUpdated;

public UpdateSubscriptionCommEvent(SubscriptionCommEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SubscriptionCommEvent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SubscriptionCommEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SubscriptionCommEvent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SubscriptionCommEvent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SubscriptionCommEvent.class);
}
success = false;
}
Event resultingEvent = new SubscriptionCommEventUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
