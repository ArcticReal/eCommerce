
package com.skytala.eCommerce.domain.accounting.relations.budget.query.glXref;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.budget.event.glXref.GlBudgetXrefFound;
import com.skytala.eCommerce.domain.accounting.relations.budget.mapper.glXref.GlBudgetXrefMapper;
import com.skytala.eCommerce.domain.accounting.relations.budget.model.glXref.GlBudgetXref;


public class FindAllGlBudgetXrefs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlBudgetXref> returnVal = new ArrayList<GlBudgetXref>();
try{
List<GenericValue> results = delegator.findAll("GlBudgetXref", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlBudgetXrefMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlBudgetXrefFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
