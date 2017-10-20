package com.skytala.eCommerce.domain.accounting.relations.budget.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.status.BudgetStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.status.BudgetStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.status.BudgetStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetStatus extends Command {

private BudgetStatus elementToBeAdded;
public AddBudgetStatus(BudgetStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BudgetStatus", elementToBeAdded.mapAttributeField());
addedElement = BudgetStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
