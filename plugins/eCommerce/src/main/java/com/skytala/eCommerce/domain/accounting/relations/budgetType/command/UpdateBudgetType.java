package com.skytala.eCommerce.domain.accounting.relations.budgetType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budgetType.event.BudgetTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetType.model.BudgetType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetType extends Command {

private BudgetType elementToBeUpdated;

public UpdateBudgetType(BudgetType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetType.class);
}
success = false;
}
Event resultingEvent = new BudgetTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}