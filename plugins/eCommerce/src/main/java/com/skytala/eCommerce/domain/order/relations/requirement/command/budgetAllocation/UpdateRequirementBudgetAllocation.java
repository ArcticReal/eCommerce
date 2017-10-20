package com.skytala.eCommerce.domain.order.relations.requirement.command.budgetAllocation;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationUpdated;
import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateRequirementBudgetAllocation extends Command {

private RequirementBudgetAllocation elementToBeUpdated;

public UpdateRequirementBudgetAllocation(RequirementBudgetAllocation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public RequirementBudgetAllocation getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(RequirementBudgetAllocation elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("RequirementBudgetAllocation", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(RequirementBudgetAllocation.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(RequirementBudgetAllocation.class);
}
success = false;
}
Event resultingEvent = new RequirementBudgetAllocationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
