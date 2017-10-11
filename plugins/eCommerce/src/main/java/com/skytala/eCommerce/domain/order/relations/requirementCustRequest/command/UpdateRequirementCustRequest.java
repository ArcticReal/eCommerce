package com.skytala.eCommerce.domain.order.relations.requirementCustRequest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event.RequirementCustRequestUpdated;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model.RequirementCustRequest;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRequirementCustRequest extends Command {

private RequirementCustRequest elementToBeUpdated;

public UpdateRequirementCustRequest(RequirementCustRequest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RequirementCustRequest getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RequirementCustRequest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RequirementCustRequest", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RequirementCustRequest.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RequirementCustRequest.class);
}
success = false;
}
Event resultingEvent = new RequirementCustRequestUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
