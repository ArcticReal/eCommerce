package com.skytala.eCommerce.domain.product.relations.subscription.command.commEvent;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscription.event.commEvent.SubscriptionCommEventAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.commEvent.SubscriptionCommEventMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.commEvent.SubscriptionCommEvent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionCommEvent extends Command {

private SubscriptionCommEvent elementToBeAdded;
public AddSubscriptionCommEvent(SubscriptionCommEvent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionCommEvent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SubscriptionCommEvent", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionCommEventMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionCommEventAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
