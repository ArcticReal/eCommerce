
package com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.event.BudgetScenarioRuleFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.mapper.BudgetScenarioRuleMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetScenarioRule.model.BudgetScenarioRule;


public class FindAllBudgetScenarioRules extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetScenarioRule> returnVal = new ArrayList<BudgetScenarioRule>();
try{
List<GenericValue> results = delegator.findAll("BudgetScenarioRule", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetScenarioRuleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetScenarioRuleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
