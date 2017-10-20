
package com.skytala.eCommerce.domain.humanres.relations.emplPosition.query.typeRate;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.event.typeRate.EmplPositionTypeRateFound;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.mapper.typeRate.EmplPositionTypeRateMapper;
import com.skytala.eCommerce.domain.humanres.relations.emplPosition.model.typeRate.EmplPositionTypeRate;


public class FindAllEmplPositionTypeRates extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmplPositionTypeRate> returnVal = new ArrayList<EmplPositionTypeRate>();
try{
List<GenericValue> results = delegator.findAll("EmplPositionTypeRate", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmplPositionTypeRateMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmplPositionTypeRateFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
