package com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.event.QuoteTermAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.mapper.QuoteTermAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.model.QuoteTermAttribute;
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
