
package com.skytala.eCommerce.domain.goodIdentificationType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.goodIdentificationType.event.GoodIdentificationTypeFound;
import com.skytala.eCommerce.domain.goodIdentificationType.mapper.GoodIdentificationTypeMapper;
import com.skytala.eCommerce.domain.goodIdentificationType.model.GoodIdentificationType;


public class FindAllGoodIdentificationTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GoodIdentificationType> returnVal = new ArrayList<GoodIdentificationType>();
try{
List<GenericValue> results = delegator.findAll("GoodIdentificationType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GoodIdentificationTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GoodIdentificationTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
