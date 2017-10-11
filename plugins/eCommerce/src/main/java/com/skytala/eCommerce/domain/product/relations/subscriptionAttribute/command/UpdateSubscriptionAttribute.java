package com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.event.SubscriptionAttributeUpdated;
import com.skytala.eCommerce.domain.product.relations.subscriptionAttribute.model.SubscriptionAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSubscriptionAttribute extends Command {

private SubscriptionAttribute elementToBeUpdated;

public UpdateSubscriptionAttribute(SubscriptionAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SubscriptionAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SubscriptionAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SubscriptionAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SubscriptionAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SubscriptionAttribute.class);
}
success = false;
}
Event resultingEvent = new SubscriptionAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
