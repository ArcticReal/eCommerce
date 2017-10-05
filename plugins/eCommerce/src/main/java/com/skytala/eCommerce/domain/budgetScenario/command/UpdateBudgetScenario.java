package com.skytala.eCommerce.domain.budgetScenario.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.budgetScenario.event.BudgetScenarioUpdated;
import com.skytala.eCommerce.domain.budgetScenario.model.BudgetScenario;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetScenario extends Command {

private BudgetScenario elementToBeUpdated;

public UpdateBudgetScenario(BudgetScenario elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetScenario getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetScenario elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetScenario", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetScenario.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetScenario.class);
}
success = false;
}
Event resultingEvent = new BudgetScenarioUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
