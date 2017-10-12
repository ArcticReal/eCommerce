
package com.skytala.eCommerce.domain.accounting.relations.budget.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.BudgetFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.BudgetMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.Budget;


public class FindAllBudgets extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Budget> returnVal = new ArrayList<Budget>();
try{
List<GenericValue> results = delegator.findAll("Budget", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
