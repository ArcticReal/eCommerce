
package com.skytala.eCommerce.domain.accounting.relations.budget.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.attribute.BudgetAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.attribute.BudgetAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.attribute.BudgetAttribute;


public class FindAllBudgetAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetAttribute> returnVal = new ArrayList<BudgetAttribute>();
try{
List<GenericValue> results = delegator.findAll("BudgetAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
