
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.event.SalesOpportunityCompetitorFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.mapper.SalesOpportunityCompetitorMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityCompetitor.model.SalesOpportunityCompetitor;


public class FindAllSalesOpportunityCompetitors extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityCompetitor> returnVal = new ArrayList<SalesOpportunityCompetitor>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityCompetitor", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityCompetitorMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityCompetitorFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
