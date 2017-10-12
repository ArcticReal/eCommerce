package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.event.SalesOpportunityQuoteUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityQuote.model.SalesOpportunityQuote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesOpportunityQuote extends Command {

private SalesOpportunityQuote elementToBeUpdated;

public UpdateSalesOpportunityQuote(SalesOpportunityQuote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesOpportunityQuote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesOpportunityQuote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesOpportunityQuote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesOpportunityQuote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesOpportunityQuote.class);
}
success = false;
}
Event resultingEvent = new SalesOpportunityQuoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
