
package com.skytala.eCommerce.domain.accounting.relations.budgetRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetRole.event.BudgetRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetRole.mapper.BudgetRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetRole.model.BudgetRole;


public class FindAllBudgetRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetRole> returnVal = new ArrayList<BudgetRole>();
try{
List<GenericValue> results = delegator.findAll("BudgetRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
