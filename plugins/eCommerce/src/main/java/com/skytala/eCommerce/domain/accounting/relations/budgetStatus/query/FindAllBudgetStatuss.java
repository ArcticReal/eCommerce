
package com.skytala.eCommerce.domain.accounting.relations.budgetStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.event.BudgetStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.mapper.BudgetStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetStatus.model.BudgetStatus;


public class FindAllBudgetStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetStatus> returnVal = new ArrayList<BudgetStatus>();
try{
List<GenericValue> results = delegator.findAll("BudgetStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
