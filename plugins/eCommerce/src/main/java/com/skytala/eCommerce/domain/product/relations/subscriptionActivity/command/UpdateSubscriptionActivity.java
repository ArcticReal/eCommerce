package com.skytala.eCommerce.domain.product.relations.subscriptionActivity.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event.SubscriptionActivityUpdated;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.model.SubscriptionActivity;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSubscriptionActivity extends Command {

private SubscriptionActivity elementToBeUpdated;

public UpdateSubscriptionActivity(SubscriptionActivity elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SubscriptionActivity getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SubscriptionActivity elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SubscriptionActivity", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SubscriptionActivity.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SubscriptionActivity.class);
}
success = false;
}
Event resultingEvent = new SubscriptionActivityUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
