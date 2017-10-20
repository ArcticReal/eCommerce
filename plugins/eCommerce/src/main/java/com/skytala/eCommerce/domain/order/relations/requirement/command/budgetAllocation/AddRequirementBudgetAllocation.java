package com.skytala.eCommerce.domain.order.relations.requirement.command.budgetAllocation;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.event.budgetAllocation.RequirementBudgetAllocationAdded;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.budgetAllocation.RequirementBudgetAllocationMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.budgetAllocation.RequirementBudgetAllocation;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddRequirementBudgetAllocation extends Command {

private RequirementBudgetAllocation elementToBeAdded;
public AddRequirementBudgetAllocation(RequirementBudgetAllocation elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

RequirementBudgetAllocation addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetItemSeqId(delegator.getNextSeqId("RequirementBudgetAllocation"));
GenericValue newValue = delegator.makeValue("RequirementBudgetAllocation", elementToBeAdded.mapAttributeField());
addedElement = RequirementBudgetAllocationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new RequirementBudgetAllocationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
