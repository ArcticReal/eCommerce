package com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.event.BudgetReviewResultTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.mapper.BudgetReviewResultTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetReviewResultType.model.BudgetReviewResultType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddBudgetReviewResultType extends Command {

private BudgetReviewResultType elementToBeAdded;
public AddBudgetReviewResultType(BudgetReviewResultType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

BudgetReviewResultType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setBudgetReviewResultTypeId(delegator.getNextSeqId("BudgetReviewResultType"));
GenericValue newValue = delegator.makeValue("BudgetReviewResultType", elementToBeAdded.mapAttributeField());
addedElement = BudgetReviewResultTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new BudgetReviewResultTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
