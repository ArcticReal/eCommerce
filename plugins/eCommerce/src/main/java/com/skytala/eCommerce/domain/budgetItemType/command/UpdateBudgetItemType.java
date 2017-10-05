package com.skytala.eCommerce.domain.budgetItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.budgetItemType.event.BudgetItemTypeUpdated;
import com.skytala.eCommerce.domain.budgetItemType.model.BudgetItemType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetItemType extends Command {

private BudgetItemType elementToBeUpdated;

public UpdateBudgetItemType(BudgetItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetItemType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetItemType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetItemType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetItemType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetItemType.class);
}
success = false;
}
Event resultingEvent = new BudgetItemTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
