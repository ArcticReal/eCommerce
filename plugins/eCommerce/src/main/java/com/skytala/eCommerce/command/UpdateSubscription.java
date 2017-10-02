package com.skytala.eCommerce.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.control.Broker;
import com.skytala.eCommerce.entity.Subscription;
import com.skytala.eCommerce.event.SubscriptionUpdated;
import com.skytala.eCommerce.exceptions.RecordNotFoundException;

public class UpdateSubscription implements Command {

private Subscription elementToBeUpdated;

public UpdateSubscription(Subscription elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Subscription getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Subscription elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public void execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Subscription", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Subscription.class); 
}
success = true;
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
success = false;
}
Broker.instance().publish(new SubscriptionUpdated(success));
}
}
