
package com.skytala.eCommerce.domain.finAccountTrans.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.finAccountTrans.event.FinAccountTransFound;
import com.skytala.eCommerce.domain.finAccountTrans.mapper.FinAccountTransMapper;
import com.skytala.eCommerce.domain.finAccountTrans.model.FinAccountTrans;


public class FindAllFinAccountTranss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTrans> returnVal = new ArrayList<FinAccountTrans>();
try{
List<GenericValue> results = delegator.findAll("FinAccountTrans", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTransMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTransFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
