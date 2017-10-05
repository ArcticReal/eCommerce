package com.skytala.eCommerce.domain.budget.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.budget.event.BudgetUpdated;
import com.skytala.eCommerce.domain.budget.model.Budget;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudget extends Command {

private Budget elementToBeUpdated;

public UpdateBudget(Budget elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Budget getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Budget elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Budget", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Budget.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Budget.class);
}
success = false;
}
Event resultingEvent = new BudgetUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
