package com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioApplication;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioApplication.BudgetScenarioApplicationUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioApplication.BudgetScenarioApplication;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetScenarioApplication extends Command {

private BudgetScenarioApplication elementToBeUpdated;

public UpdateBudgetScenarioApplication(BudgetScenarioApplication elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetScenarioApplication getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetScenarioApplication elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetScenarioApplication", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetScenarioApplication.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetScenarioApplication.class);
}
success = false;
}
Event resultingEvent = new BudgetScenarioApplicationUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
