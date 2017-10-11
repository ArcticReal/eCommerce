package com.skytala.eCommerce.domain.product.relations.marketInterest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.marketInterest.event.MarketInterestAdded;
import com.skytala.eCommerce.domain.product.relations.marketInterest.mapper.MarketInterestMapper;
import com.skytala.eCommerce.domain.product.relations.marketInterest.model.MarketInterest;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMarketInterest extends Command {

private MarketInterest elementToBeAdded;
public AddMarketInterest(MarketInterest elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MarketInterest addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("MarketInterest", elementToBeAdded.mapAttributeField());
addedElement = MarketInterestMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MarketInterestAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
