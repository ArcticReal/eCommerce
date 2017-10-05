
package com.skytala.eCommerce.domain.salesOpportunity.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.salesOpportunity.event.SalesOpportunityFound;
import com.skytala.eCommerce.domain.salesOpportunity.mapper.SalesOpportunityMapper;
import com.skytala.eCommerce.domain.salesOpportunity.model.SalesOpportunity;


public class FindAllSalesOpportunitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunity> returnVal = new ArrayList<SalesOpportunity>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunity", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
