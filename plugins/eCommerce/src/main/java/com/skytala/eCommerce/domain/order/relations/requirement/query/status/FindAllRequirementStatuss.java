
package com.skytala.eCommerce.domain.order.relations.requirement.query.status;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirement.event.status.RequirementStatusFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.status.RequirementStatusMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.status.RequirementStatus;


public class FindAllRequirementStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementStatus> returnVal = new ArrayList<RequirementStatus>();
try{
List<GenericValue> results = delegator.findAll("RequirementStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
