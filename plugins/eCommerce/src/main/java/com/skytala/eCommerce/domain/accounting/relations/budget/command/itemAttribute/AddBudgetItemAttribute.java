package com.skytala.eCommerce.domain.accounting.relations.budget.command.itemAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.itemAttribute.BudgetItemAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.itemAttribute.BudgetItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.itemAttribute.BudgetItemAttribute;
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
