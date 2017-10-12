package com.skytala.eCommerce.domain.accounting.relations.budgetScenario.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenario.event.BudgetScenarioAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenario.mapper.BudgetScenarioMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenario.model.BudgetScenario;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetScenario extends Command {

private BudgetScenario elementToBeAdded;
public AddBudgetScenario(BudgetScenario elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetScenario addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetScenarioId(delegator.getNextSeqId("BudgetScenario"));
GenericValue newValue = delegator.makeValue("BudgetScenario", elementToBeAdded.mapAttributeField());
addedElement = BudgetScenarioMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetScenarioAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
