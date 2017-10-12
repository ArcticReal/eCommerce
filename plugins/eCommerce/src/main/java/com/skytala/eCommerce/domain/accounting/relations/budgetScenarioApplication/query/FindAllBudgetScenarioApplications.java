
package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.event.BudgetScenarioApplicationFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.mapper.BudgetScenarioApplicationMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioApplication.model.BudgetScenarioApplication;


public class FindAllBudgetScenarioApplications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetScenarioApplication> returnVal = new ArrayList<BudgetScenarioApplication>();
try{
List<GenericValue> results = delegator.findAll("BudgetScenarioApplication", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetScenarioApplicationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetScenarioApplicationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
