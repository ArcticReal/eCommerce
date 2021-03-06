package com.skytala.eCommerce.domain.accounting.relations.budget.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.BudgetAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.BudgetMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.Budget;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudget extends Command {

private Budget elementToBeAdded;
public AddBudget(Budget elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Budget addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetId(delegator.getNextSeqId("Budget"));
GenericValue newValue = delegator.makeValue("Budget", elementToBeAdded.mapAttributeField());
addedElement = BudgetMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
