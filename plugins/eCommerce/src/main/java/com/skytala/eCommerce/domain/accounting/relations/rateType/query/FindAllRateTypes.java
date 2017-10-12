
package com.skytala.eCommerce.domain.accounting.relations.rateType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.rateType.event.RateTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.rateType.mapper.RateTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.rateType.model.RateType;


public class FindAllRateTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RateType> returnVal = new ArrayList<RateType>();
try{
List<GenericValue> results = delegator.findAll("RateType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RateTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RateTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
