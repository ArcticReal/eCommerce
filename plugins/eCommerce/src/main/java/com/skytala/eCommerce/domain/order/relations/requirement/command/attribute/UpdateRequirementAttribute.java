package com.skytala.eCommerce.domain.order.relations.requirement.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.requirement.event.attribute.RequirementAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.model.attribute.RequirementAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRequirementAttribute extends Command {

private RequirementAttribute elementToBeUpdated;

public UpdateRequirementAttribute(RequirementAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RequirementAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RequirementAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RequirementAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RequirementAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RequirementAttribute.class);
}
success = false;
}
Event resultingEvent = new RequirementAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
