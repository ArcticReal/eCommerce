package com.skytala.eCommerce.domain.order.relations.quote.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.quote.event.attribute.QuoteAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.model.attribute.QuoteAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteAttribute extends Command {

private QuoteAttribute elementToBeUpdated;

public UpdateQuoteAttribute(QuoteAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteAttribute.class);
}
success = false;
}
Event resultingEvent = new QuoteAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
