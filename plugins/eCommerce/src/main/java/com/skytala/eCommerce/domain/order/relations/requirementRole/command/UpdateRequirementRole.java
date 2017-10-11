package com.skytala.eCommerce.domain.order.relations.requirementRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.requirementRole.event.RequirementRoleUpdated;
import com.skytala.eCommerce.domain.order.relations.requirementRole.model.RequirementRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRequirementRole extends Command {

private RequirementRole elementToBeUpdated;

public UpdateRequirementRole(RequirementRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RequirementRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RequirementRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RequirementRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RequirementRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RequirementRole.class);
}
success = false;
}
Event resultingEvent = new RequirementRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
