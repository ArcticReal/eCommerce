
package com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.event.EmplPositionResponsibilityFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.mapper.EmplPositionResponsibilityMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionResponsibility.model.EmplPositionResponsibility;


public class FindAllEmplPositionResponsibilitys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionResponsibility> returnVal = new ArrayList<EmplPositionResponsibility>();
try{
List<GenericValue> results = delegator.findAll("EmplPositionResponsibility", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionResponsibilityMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionResponsibilityFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
