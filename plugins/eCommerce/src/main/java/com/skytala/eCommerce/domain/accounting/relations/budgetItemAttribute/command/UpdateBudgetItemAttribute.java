package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event.BudgetItemAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetItemAttribute extends Command {

private BudgetItemAttribute elementToBeUpdated;

public UpdateBudgetItemAttribute(BudgetItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetItemAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetItemAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetItemAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetItemAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetItemAttribute.class);
}
success = false;
}
Event resultingEvent = new BudgetItemAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
