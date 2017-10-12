
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.event.SalesOpportunityStageFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.mapper.SalesOpportunityStageMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityStage.model.SalesOpportunityStage;


public class FindAllSalesOpportunityStages extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityStage> returnVal = new ArrayList<SalesOpportunityStage>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityStage", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityStageMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityStageFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
