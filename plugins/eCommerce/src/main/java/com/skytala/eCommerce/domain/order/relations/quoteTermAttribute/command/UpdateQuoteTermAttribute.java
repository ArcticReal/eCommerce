package com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.event.QuoteTermAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.quoteTermAttribute.model.QuoteTermAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteTermAttribute extends Command {

private QuoteTermAttribute elementToBeUpdated;

public UpdateQuoteTermAttribute(QuoteTermAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteTermAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteTermAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteTermAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteTermAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteTermAttribute.class);
}
success = false;
}
Event resultingEvent = new QuoteTermAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
