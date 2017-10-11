
package com.skytala.eCommerce.domain.order.relations.requirementCustRequest.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.event.RequirementCustRequestFound;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.mapper.RequirementCustRequestMapper;
import com.skytala.eCommerce.domain.order.relations.requirementCustRequest.model.RequirementCustRequest;


public class FindAllRequirementCustRequests extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementCustRequest> returnVal = new ArrayList<RequirementCustRequest>();
try{
List<GenericValue> results = delegator.findAll("RequirementCustRequest", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementCustRequestMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementCustRequestFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
