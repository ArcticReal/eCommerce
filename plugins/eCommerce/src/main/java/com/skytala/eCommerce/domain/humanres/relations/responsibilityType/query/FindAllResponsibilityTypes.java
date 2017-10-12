
package com.skytala.eCommerce.domain.humanres.relations.responsibilityType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.event.ResponsibilityTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.mapper.ResponsibilityTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.responsibilityType.model.ResponsibilityType;


public class FindAllResponsibilityTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ResponsibilityType> returnVal = new ArrayList<ResponsibilityType>();
try{
List<GenericValue> results = delegator.findAll("ResponsibilityType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ResponsibilityTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ResponsibilityTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
