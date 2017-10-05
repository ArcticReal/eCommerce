package com.skytala.eCommerce.domain.quote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.quote.event.QuoteUpdated;
import com.skytala.eCommerce.domain.quote.model.Quote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuote extends Command {

private Quote elementToBeUpdated;

public UpdateQuote(Quote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Quote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Quote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Quote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Quote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Quote.class);
}
success = false;
}
Event resultingEvent = new QuoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
