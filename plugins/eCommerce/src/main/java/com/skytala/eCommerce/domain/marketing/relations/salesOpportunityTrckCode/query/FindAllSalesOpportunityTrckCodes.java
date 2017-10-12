
package com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.event.SalesOpportunityTrckCodeFound;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.mapper.SalesOpportunityTrckCodeMapper;
import com.skytala.eCommerce.domain.marketing.relations.salesOpportunityTrckCode.model.SalesOpportunityTrckCode;


public class FindAllSalesOpportunityTrckCodes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SalesOpportunityTrckCode> returnVal = new ArrayList<SalesOpportunityTrckCode>();
try{
List<GenericValue> results = delegator.findAll("SalesOpportunityTrckCode", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SalesOpportunityTrckCodeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SalesOpportunityTrckCodeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
