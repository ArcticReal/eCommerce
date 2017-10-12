package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.event.SalesOpportunityTrckCodeUpdated;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSalesOpportunityTrckCode extends Command {

private SalesOpportunityTrckCode elementToBeUpdated;

public UpdateSalesOpportunityTrckCode(SalesOpportunityTrckCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SalesOpportunityTrckCode getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SalesOpportunityTrckCode elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SalesOpportunityTrckCode", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SalesOpportunityTrckCode.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SalesOpportunityTrckCode.class);
}
success = false;
}
Event resultingEvent = new SalesOpportunityTrckCodeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
