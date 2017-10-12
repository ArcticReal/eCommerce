package com.skytala.eCommerce.domain.accounting.relations.budgetStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.event.BudgetStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.model.BudgetStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetStatus extends Command {

private BudgetStatus elementToBeUpdated;

public UpdateBudgetStatus(BudgetStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetStatus.class);
}
success = false;
}
Event resultingEvent = new BudgetStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
