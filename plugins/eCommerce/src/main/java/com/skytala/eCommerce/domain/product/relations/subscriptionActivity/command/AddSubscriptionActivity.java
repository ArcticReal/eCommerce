package com.skytala.eCommerce.domain.product.relations.subscriptionActivity.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.event.SubscriptionActivityAdded;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.mapper.SubscriptionActivityMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionActivity.model.SubscriptionActivity;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionActivity extends Command {

private SubscriptionActivity elementToBeAdded;
public AddSubscriptionActivity(SubscriptionActivity elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionActivity addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSubscriptionActivityId(delegator.getNextSeqId("SubscriptionActivity"));
GenericValue newValue = delegator.makeValue("SubscriptionActivity", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionActivityMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionActivityAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
