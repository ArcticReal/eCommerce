package com.skytala.eCommerce.domain.order.relations.quote.command.coefficient;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.quote.event.coefficient.QuoteCoefficientUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.model.coefficient.QuoteCoefficient;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteCoefficient extends Command {

private QuoteCoefficient elementToBeUpdated;

public UpdateQuoteCoefficient(QuoteCoefficient elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteCoefficient getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteCoefficient elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteCoefficient", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteCoefficient.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteCoefficient.class);
}
success = false;
}
Event resultingEvent = new QuoteCoefficientUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
