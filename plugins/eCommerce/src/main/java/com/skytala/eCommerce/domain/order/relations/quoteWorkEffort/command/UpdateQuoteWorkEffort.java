package com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.event.QuoteWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.quoteWorkEffort.model.QuoteWorkEffort;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteWorkEffort extends Command {

private QuoteWorkEffort elementToBeUpdated;

public UpdateQuoteWorkEffort(QuoteWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteWorkEffort getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteWorkEffort", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteWorkEffort.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteWorkEffort.class);
}
success = false;
}
Event resultingEvent = new QuoteWorkEffortUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
