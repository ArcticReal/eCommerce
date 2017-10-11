package com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.event.SubscriptionFulfillmentPieceAdded;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.mapper.SubscriptionFulfillmentPieceMapper;
import com.skytala.eCommerce.domain.product.relations.subscriptionFulfillmentPiece.model.SubscriptionFulfillmentPiece;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSubscriptionFulfillmentPiece extends Command {

private SubscriptionFulfillmentPiece elementToBeAdded;
public AddSubscriptionFulfillmentPiece(SubscriptionFulfillmentPiece elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SubscriptionFulfillmentPiece addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("SubscriptionFulfillmentPiece", elementToBeAdded.mapAttributeField());
addedElement = SubscriptionFulfillmentPieceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SubscriptionFulfillmentPieceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
