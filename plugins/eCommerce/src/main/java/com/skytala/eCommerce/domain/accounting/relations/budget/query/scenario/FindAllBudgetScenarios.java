
package com.skytala.eCommerce.domain.accounting.relations.budget.query.scenario;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.scenario.BudgetScenarioFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.scenario.BudgetScenarioMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.scenario.BudgetScenario;


public class FindAllBudgetScenarios extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetScenario> returnVal = new ArrayList<BudgetScenario>();
try{
List<GenericValue> results = delegator.findAll("BudgetScenario", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetScenarioMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetScenarioFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
