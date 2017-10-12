package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.event.SalesOpportunityWorkEffortUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.model.SalesOpportunityWorkEffort;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesOpportunityWorkEffort extends Command {

private SalesOpportunityWorkEffort elementToBeUpdated;

public UpdateSalesOpportunityWorkEffort(SalesOpportunityWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesOpportunityWorkEffort getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesOpportunityWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesOpportunityWorkEffort", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesOpportunityWorkEffort.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesOpportunityWorkEffort.class);
}
success = false;
}
Event resultingEvent = new SalesOpportunityWorkEffortUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
