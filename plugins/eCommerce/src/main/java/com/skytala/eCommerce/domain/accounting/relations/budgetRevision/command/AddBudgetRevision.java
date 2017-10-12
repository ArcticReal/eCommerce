package com.skytala.eCommerce.domain.accounting.relations.budgetRevision.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevision.event.BudgetRevisionAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevision.mapper.BudgetRevisionMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetRevision.model.BudgetRevision;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetRevision extends Command {

private BudgetRevision elementToBeAdded;
public AddBudgetRevision(BudgetRevision elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetRevision addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRevisionSeqId(delegator.getNextSeqId("BudgetRevision"));
GenericValue newValue = delegator.makeValue("BudgetRevision", elementToBeAdded.mapAttributeField());
addedElement = BudgetRevisionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetRevisionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
