package com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.event.BudgetAttributeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.mapper.BudgetAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetAttribute.model.BudgetAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetAttribute extends Command {

private BudgetAttribute elementToBeAdded;
public AddBudgetAttribute(BudgetAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BudgetAttribute", elementToBeAdded.mapAttributeField());
addedElement = BudgetAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
