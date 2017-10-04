package com.skytala.eCommerce.domain.subscription.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.subscription.event.SubscriptionAdded;
import com.skytala.eCommerce.domain.subscription.mapper.SubscriptionMapper;
import com.skytala.eCommerce.domain.subscription.model.Subscription;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscription extends Command {

private Subscription elementToBeAdded;
public AddSubscription(Subscription elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Subscription addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSubscriptionId(delegator.getNextSeqId("Subscription"));
GenericValue newValue = delegator.makeValue("Subscription", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
