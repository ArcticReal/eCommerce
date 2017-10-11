
package com.skytala.eCommerce.domain.order.relations.requirement.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirement.event.RequirementFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.RequirementMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.Requirement;


public class FindAllRequirements extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Requirement> returnVal = new ArrayList<Requirement>();
try{
List<GenericValue> results = delegator.findAll("Requirement", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
