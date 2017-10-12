package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.mapper.BudgetItemTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetItemTypeAttr extends Command {

private BudgetItemTypeAttr elementToBeAdded;
public AddBudgetItemTypeAttr(BudgetItemTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetItemTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BudgetItemTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = BudgetItemTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetItemTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
