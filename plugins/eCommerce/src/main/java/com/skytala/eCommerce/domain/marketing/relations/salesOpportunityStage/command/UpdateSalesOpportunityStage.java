package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.event.SalesOpportunityStageUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.model.SalesOpportunityStage;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesOpportunityStage extends Command {

private SalesOpportunityStage elementToBeUpdated;

public UpdateSalesOpportunityStage(SalesOpportunityStage elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesOpportunityStage getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesOpportunityStage elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesOpportunityStage", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesOpportunityStage.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesOpportunityStage.class);
}
success = false;
}
Event resultingEvent = new SalesOpportunityStageUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
