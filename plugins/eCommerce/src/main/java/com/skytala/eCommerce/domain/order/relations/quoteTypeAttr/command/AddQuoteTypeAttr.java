package com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.event.QuoteTypeAttrAdded;
import com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.mapper.QuoteTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.quoteTypeAttr.model.QuoteTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteTypeAttr extends Command {

private QuoteTypeAttr elementToBeAdded;
public AddQuoteTypeAttr(QuoteTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("QuoteTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = QuoteTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
