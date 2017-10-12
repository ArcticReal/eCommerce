
package com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.event.EmplPositionTypeClassFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.mapper.EmplPositionTypeClassMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPositionTypeClass.model.EmplPositionTypeClass;


public class FindAllEmplPositionTypeClasss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionTypeClass> returnVal = new ArrayList<EmplPositionTypeClass>();
try{
List<GenericValue> results = delegator.findAll("EmplPositionTypeClass", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionTypeClassMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionTypeClassFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
