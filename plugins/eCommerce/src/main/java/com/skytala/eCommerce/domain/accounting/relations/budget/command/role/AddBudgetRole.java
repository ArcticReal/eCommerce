package com.skytala.eCommerce.domain.accounting.relations.budget.command.role;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.role.BudgetRoleAdded;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.role.BudgetRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.role.BudgetRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetRole extends Command {

private BudgetRole elementToBeAdded;
public AddBudgetRole(BudgetRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("BudgetRole"));
GenericValue newValue = delegator.makeValue("BudgetRole", elementToBeAdded.mapAttributeField());
addedElement = BudgetRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
