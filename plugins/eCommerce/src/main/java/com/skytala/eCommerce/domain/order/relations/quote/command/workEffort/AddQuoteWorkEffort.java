package com.skytala.eCommerce.domain.order.relations.quote.command.workEffort;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.event.workEffort.QuoteWorkEffortAdded;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.workEffort.QuoteWorkEffortMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.workEffort.QuoteWorkEffort;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteWorkEffort extends Command {

private QuoteWorkEffort elementToBeAdded;
public AddQuoteWorkEffort(QuoteWorkEffort elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteWorkEffort addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("QuoteWorkEffort", elementToBeAdded.mapAttributeField());
addedElement = QuoteWorkEffortMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteWorkEffortAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
