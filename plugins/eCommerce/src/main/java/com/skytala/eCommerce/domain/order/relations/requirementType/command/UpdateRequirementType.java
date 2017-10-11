package com.skytala.eCommerce.domain.order.relations.requirementType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.requirementType.event.RequirementTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.requirementType.model.RequirementType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRequirementType extends Command {

private RequirementType elementToBeUpdated;

public UpdateRequirementType(RequirementType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RequirementType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RequirementType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RequirementType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RequirementType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RequirementType.class);
}
success = false;
}
Event resultingEvent = new RequirementTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
