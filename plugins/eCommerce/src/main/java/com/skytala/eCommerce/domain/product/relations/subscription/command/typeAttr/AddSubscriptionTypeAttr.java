package com.skytala.eCommerce.domain.product.relations.subscription.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscription.event.typeAttr.SubscriptionTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.subscription.mapper.typeAttr.SubscriptionTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.subscription.model.typeAttr.SubscriptionTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionTypeAttr extends Command {

private SubscriptionTypeAttr elementToBeAdded;
public AddSubscriptionTypeAttr(SubscriptionTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionTypeAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("SubscriptionTypeAttr"));
GenericValue newValue = delegator.makeValue("SubscriptionTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
