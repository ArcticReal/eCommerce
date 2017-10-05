package com.skytala.eCommerce.domain.subscriptionType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.subscriptionType.event.SubscriptionTypeUpdated;
import com.skytala.eCommerce.domain.subscriptionType.model.SubscriptionType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSubscriptionType extends Command {

private SubscriptionType elementToBeUpdated;

public UpdateSubscriptionType(SubscriptionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SubscriptionType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SubscriptionType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SubscriptionType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SubscriptionType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SubscriptionType.class);
}
success = false;
}
Event resultingEvent = new SubscriptionTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
