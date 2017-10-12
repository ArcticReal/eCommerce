package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.event.BudgetScenarioRuleAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.mapper.BudgetScenarioRuleMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.model.BudgetScenarioRule;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetScenarioRule extends Command {

private BudgetScenarioRule elementToBeAdded;
public AddBudgetScenarioRule(BudgetScenarioRule elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetScenarioRule addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BudgetScenarioRule", elementToBeAdded.mapAttributeField());
addedElement = BudgetScenarioRuleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetScenarioRuleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
