package com.skytala.eCommerce.domain.accounting.relations.budget.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.role.BudgetRoleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.role.BudgetRole;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetRole extends Command {

private BudgetRole elementToBeUpdated;

public UpdateBudgetRole(BudgetRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetRole getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetRole elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetRole", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetRole.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetRole.class);
}
success = false;
}
Event resultingEvent = new BudgetRoleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
