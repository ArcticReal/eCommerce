package com.skytala.eCommerce.domain.order.relations.quoteType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteType.event.QuoteTypeAdded;
import com.skytala.eCommerce.domain.order.relations.quoteType.mapper.QuoteTypeMapper;
import com.skytala.eCommerce.domain.order.relations.quoteType.model.QuoteType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteType extends Command {

private QuoteType elementToBeAdded;
public AddQuoteType(QuoteType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setQuoteTypeId(delegator.getNextSeqId("QuoteType"));
GenericValue newValue = delegator.makeValue("QuoteType", elementToBeAdded.mapAttributeField());
addedElement = QuoteTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
