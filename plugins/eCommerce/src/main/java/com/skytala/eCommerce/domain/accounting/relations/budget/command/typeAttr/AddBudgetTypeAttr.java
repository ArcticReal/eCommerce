package com.skytala.eCommerce.domain.accounting.relations.budget.command.typeAttr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.typeAttr.BudgetTypeAttrAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.typeAttr.BudgetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.typeAttr.BudgetTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetTypeAttr extends Command {

private BudgetTypeAttr elementToBeAdded;
public AddBudgetTypeAttr(BudgetTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("BudgetTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = BudgetTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
