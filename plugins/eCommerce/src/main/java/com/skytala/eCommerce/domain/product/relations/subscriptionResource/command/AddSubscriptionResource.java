package com.skytala.eCommerce.domain.product.relations.subscriptionResource.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.event.SubscriptionResourceAdded;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.mapper.SubscriptionResourceMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionResource.model.SubscriptionResource;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionResource extends Command {

private SubscriptionResource elementToBeAdded;
public AddSubscriptionResource(SubscriptionResource elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionResource addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSubscriptionResourceId(delegator.getNextSeqId("SubscriptionResource"));
GenericValue newValue = delegator.makeValue("SubscriptionResource", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionResourceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionResourceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
