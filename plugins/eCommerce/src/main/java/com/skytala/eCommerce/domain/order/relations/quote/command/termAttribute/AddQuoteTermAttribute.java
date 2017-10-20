package com.skytala.eCommerce.domain.order.relations.quote.command.termAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.event.termAttribute.QuoteTermAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.termAttribute.QuoteTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.termAttribute.QuoteTermAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteTermAttribute extends Command {

private QuoteTermAttribute elementToBeAdded;
public AddQuoteTermAttribute(QuoteTermAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteTermAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("QuoteTermAttribute", elementToBeAdded.mapAttributeField());
addedElement = QuoteTermAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteTermAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
