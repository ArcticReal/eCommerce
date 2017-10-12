package com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.event.BudgetRevisionImpactAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.mapper.BudgetRevisionImpactMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevisionImpact.model.BudgetRevisionImpact;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetRevisionImpact extends Command {

private BudgetRevisionImpact elementToBeAdded;
public AddBudgetRevisionImpact(BudgetRevisionImpact elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetRevisionImpact addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BudgetRevisionImpact", elementToBeAdded.mapAttributeField());
addedElement = BudgetRevisionImpactMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetRevisionImpactAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
