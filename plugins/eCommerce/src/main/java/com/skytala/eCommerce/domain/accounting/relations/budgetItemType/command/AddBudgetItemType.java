package com.skytala.eCommerce.domain.accounting.relations.budgetItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemType.event.BudgetItemTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemType.mapper.BudgetItemTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemType.model.BudgetItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetItemType extends Command {

private BudgetItemType elementToBeAdded;
public AddBudgetItemType(BudgetItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetItemTypeId(delegator.getNextSeqId("BudgetItemType"));
GenericValue newValue = delegator.makeValue("BudgetItemType", elementToBeAdded.mapAttributeField());
addedElement = BudgetItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
