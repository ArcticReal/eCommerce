
package com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.event.BudgetItemAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.mapper.BudgetItemAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetItemAttribute.model.BudgetItemAttribute;


public class FindAllBudgetItemAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetItemAttribute> returnVal = new ArrayList<BudgetItemAttribute>();
try{
List<GenericValue> results = delegator.findAll("BudgetItemAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetItemAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetItemAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
