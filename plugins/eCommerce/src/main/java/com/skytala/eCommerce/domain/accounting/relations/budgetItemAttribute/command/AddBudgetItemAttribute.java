package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event.BudgetItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.mapper.BudgetItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetItemAttribute extends Command {

private BudgetItemAttribute elementToBeAdded;
public AddBudgetItemAttribute(BudgetItemAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetItemAttribute addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetItemSeqId(delegator.getNextSeqId("BudgetItemAttribute"));
GenericValue newValue = delegator.makeValue("BudgetItemAttribute", elementToBeAdded.mapAttributeField());
addedElement = BudgetItemAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetItemAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
