package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.event.SalesOpportunityRoleUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityRole.model.SalesOpportunityRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesOpportunityRole extends Command {

private SalesOpportunityRole elementToBeUpdated;

public UpdateSalesOpportunityRole(SalesOpportunityRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesOpportunityRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesOpportunityRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesOpportunityRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesOpportunityRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesOpportunityRole.class);
}
success = false;
}
Event resultingEvent = new SalesOpportunityRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
