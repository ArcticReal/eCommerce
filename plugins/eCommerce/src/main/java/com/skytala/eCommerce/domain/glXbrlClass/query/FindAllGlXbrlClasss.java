
package com.skytala.eCommerce.domain.glXbrlClass.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glXbrlClass.event.GlXbrlClassFound;
import com.skytala.eCommerce.domain.glXbrlClass.mapper.GlXbrlClassMapper;
import com.skytala.eCommerce.domain.glXbrlClass.model.GlXbrlClass;


public class FindAllGlXbrlClasss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlXbrlClass> returnVal = new ArrayList<GlXbrlClass>();
try{
List<GenericValue> results = delegator.findAll("GlXbrlClass", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlXbrlClassMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlXbrlClassFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
