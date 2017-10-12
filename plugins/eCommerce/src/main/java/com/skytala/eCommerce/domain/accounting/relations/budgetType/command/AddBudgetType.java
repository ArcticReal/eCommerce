package com.skytala.eCommerce.domain.accounting.relations.budgetType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetType.event.BudgetTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetType.mapper.BudgetTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetType.model.BudgetType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetType extends Command {

private BudgetType elementToBeAdded;
public AddBudgetType(BudgetType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetTypeId(delegator.getNextSeqId("BudgetType"));
GenericValue newValue = delegator.makeValue("BudgetType", elementToBeAdded.mapAttributeField());
addedElement = BudgetTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
