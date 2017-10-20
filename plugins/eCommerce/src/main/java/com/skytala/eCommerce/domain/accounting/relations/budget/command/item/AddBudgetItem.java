package com.skytala.eCommerce.domain.accounting.relations.budget.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.item.BudgetItemAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.item.BudgetItemMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.item.BudgetItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetItem extends Command {

private BudgetItem elementToBeAdded;
public AddBudgetItem(BudgetItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetItemSeqId(delegator.getNextSeqId("BudgetItem"));
GenericValue newValue = delegator.makeValue("BudgetItem", elementToBeAdded.mapAttributeField());
addedElement = BudgetItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
