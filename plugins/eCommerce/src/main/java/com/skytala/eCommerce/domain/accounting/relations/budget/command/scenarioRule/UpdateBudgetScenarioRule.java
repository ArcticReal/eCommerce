package com.skytala.eCommerce.domain.accounting.relations.budget.command.scenarioRule;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenarioRule.BudgetScenarioRuleUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenarioRule.BudgetScenarioRule;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetScenarioRule extends Command {

private BudgetScenarioRule elementToBeUpdated;

public UpdateBudgetScenarioRule(BudgetScenarioRule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetScenarioRule getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetScenarioRule elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetScenarioRule", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetScenarioRule.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetScenarioRule.class);
}
success = false;
}
Event resultingEvent = new BudgetScenarioRuleUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
