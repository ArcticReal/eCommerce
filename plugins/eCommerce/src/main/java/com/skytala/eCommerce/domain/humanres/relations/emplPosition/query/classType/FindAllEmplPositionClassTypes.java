
package com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.classType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.classType.EmplPositionClassTypeFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.classType.EmplPositionClassTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.classType.EmplPositionClassType;


public class FindAllEmplPositionClassTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionClassType> returnVal = new ArrayList<EmplPositionClassType>();
try{
List<GenericValue> results = delegator.findAll("EmplPositionClassType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionClassTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionClassTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
