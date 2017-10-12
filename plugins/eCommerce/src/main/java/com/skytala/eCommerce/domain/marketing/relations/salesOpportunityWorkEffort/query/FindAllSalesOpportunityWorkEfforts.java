
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.event.SalesOpportunityWorkEffortFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.mapper.SalesOpportunityWorkEffortMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityWorkEffort.model.SalesOpportunityWorkEffort;


public class FindAllSalesOpportunityWorkEfforts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityWorkEffort> returnVal = new ArrayList<SalesOpportunityWorkEffort>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityWorkEffort", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityWorkEffortMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityWorkEffortFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
