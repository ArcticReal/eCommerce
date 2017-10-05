
package com.skytala.eCommerce.domain.needType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.needType.event.NeedTypeFound;
import com.skytala.eCommerce.domain.needType.mapper.NeedTypeMapper;
import com.skytala.eCommerce.domain.needType.model.NeedType;


public class FindAllNeedTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<NeedType> returnVal = new ArrayList<NeedType>();
try{
List<GenericValue> results = delegator.findAll("NeedType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(NeedTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new NeedTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
