
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.query.history;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.event.history.SalesOpportunityHistoryFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.mapper.history.SalesOpportunityHistoryMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunity.model.history.SalesOpportunityHistory;


public class FindAllSalesOpportunityHistorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityHistory> returnVal = new ArrayList<SalesOpportunityHistory>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityHistory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityHistoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityHistoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
