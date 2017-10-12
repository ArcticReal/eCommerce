package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.event.BudgetScenarioApplicationAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.mapper.BudgetScenarioApplicationMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.model.BudgetScenarioApplication;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetScenarioApplication extends Command {

private BudgetScenarioApplication elementToBeAdded;
public AddBudgetScenarioApplication(BudgetScenarioApplication elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetScenarioApplication addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetScenarioApplicId(delegator.getNextSeqId("BudgetScenarioApplication"));
GenericValue newValue = delegator.makeValue("BudgetScenarioApplication", elementToBeAdded.mapAttributeField());
addedElement = BudgetScenarioApplicationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetScenarioApplicationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
