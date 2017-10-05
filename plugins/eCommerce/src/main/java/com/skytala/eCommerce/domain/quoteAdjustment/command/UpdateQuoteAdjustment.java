package com.skytala.eCommerce.domain.quoteAdjustment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.quoteAdjustment.event.QuoteAdjustmentUpdated;
import com.skytala.eCommerce.domain.quoteAdjustment.model.QuoteAdjustment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteAdjustment extends Command {

private QuoteAdjustment elementToBeUpdated;

public UpdateQuoteAdjustment(QuoteAdjustment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteAdjustment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteAdjustment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteAdjustment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteAdjustment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteAdjustment.class);
}
success = false;
}
Event resultingEvent = new QuoteAdjustmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
