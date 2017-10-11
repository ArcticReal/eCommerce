
package com.skytala.eCommerce.domain.order.relations.requirementAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.event.RequirementAttributeFound;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.mapper.RequirementAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.requirementAttribute.model.RequirementAttribute;


public class FindAllRequirementAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementAttribute> returnVal = new ArrayList<RequirementAttribute>();
try{
List<GenericValue> results = delegator.findAll("RequirementAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
