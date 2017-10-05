
package com.skytala.eCommerce.domain.returnType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.returnType.event.ReturnTypeFound;
import com.skytala.eCommerce.domain.returnType.mapper.ReturnTypeMapper;
import com.skytala.eCommerce.domain.returnType.model.ReturnType;


public class FindAllReturnTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ReturnType> returnVal = new ArrayList<ReturnType>();
try{
List<GenericValue> results = delegator.findAll("ReturnType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ReturnTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ReturnTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
