package com.skytala.eCommerce.domain.order.relations.quoteAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteAttribute.event.QuoteAttributeAdded;
import com.skytala.eCommerce.domain.order.relations.quoteAttribute.mapper.QuoteAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.quoteAttribute.model.QuoteAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteAttribute extends Command {

private QuoteAttribute elementToBeAdded;
public AddQuoteAttribute(QuoteAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("QuoteAttribute", elementToBeAdded.mapAttributeField());
addedElement = QuoteAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
