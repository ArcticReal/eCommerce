package com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.event.BudgetItemTypeAttrUpdated;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemTypeAttr.model.BudgetItemTypeAttr;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateBudgetItemTypeAttr extends Command {

private BudgetItemTypeAttr elementToBeUpdated;

public UpdateBudgetItemTypeAttr(BudgetItemTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public BudgetItemTypeAttr getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(BudgetItemTypeAttr elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("BudgetItemTypeAttr", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(BudgetItemTypeAttr.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(BudgetItemTypeAttr.class);
}
success = false;
}
Event resultingEvent = new BudgetItemTypeAttrUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
