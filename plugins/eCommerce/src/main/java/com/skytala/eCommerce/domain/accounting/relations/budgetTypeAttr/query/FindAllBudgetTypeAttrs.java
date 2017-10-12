
package com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.event.BudgetTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.mapper.BudgetTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.budgetTypeAttr.model.BudgetTypeAttr;


public class FindAllBudgetTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BudgetTypeAttr> returnVal = new ArrayList<BudgetTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("BudgetTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BudgetTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BudgetTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
