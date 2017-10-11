
package com.skytala.eCommerce.domain.order.relations.requirementType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirementType.event.RequirementTypeFound;
import com.skytala.eCommerce.domain.order.relations.requirementType.mapper.RequirementTypeMapper;
import com.skytala.eCommerce.domain.order.relations.requirementType.model.RequirementType;


public class FindAllRequirementTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementType> returnVal = new ArrayList<RequirementType>();
try{
List<GenericValue> results = delegator.findAll("RequirementType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
