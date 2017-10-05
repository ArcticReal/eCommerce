package com.skytala.eCommerce.domain.quoteType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.quoteType.event.QuoteTypeUpdated;
import com.skytala.eCommerce.domain.quoteType.model.QuoteType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteType extends Command {

private QuoteType elementToBeUpdated;

public UpdateQuoteType(QuoteType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteType.class);
}
success = false;
}
Event resultingEvent = new QuoteTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
