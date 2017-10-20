
package com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.type.EmplPositionTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.type.EmplPositionTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.type.EmplPositionType;


public class FindAllEmplPositionTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionType> returnVal = new ArrayList<EmplPositionType>();
try{
List<GenericValue> results = delegator.findAll("EmplPositionType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
