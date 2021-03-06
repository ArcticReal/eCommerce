package com.skytala.eCommerce.domain.order.relations.quote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.event.QuoteAdded;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.QuoteMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.Quote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuote extends Command {

private Quote elementToBeAdded;
public AddQuote(Quote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Quote addedElement = null;
boolean success = false;
try {
elementToBeAdded.setQuoteId(delegator.getNextSeqId("Quote"));
GenericValue newValue = delegator.makeValue("Quote", elementToBeAdded.mapAttributeField());
addedElement = QuoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
