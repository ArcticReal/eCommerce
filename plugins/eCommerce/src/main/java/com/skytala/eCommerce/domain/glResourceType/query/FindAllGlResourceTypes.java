
package com.skytala.eCommerce.domain.glResourceType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glResourceType.event.GlResourceTypeFound;
import com.skytala.eCommerce.domain.glResourceType.mapper.GlResourceTypeMapper;
import com.skytala.eCommerce.domain.glResourceType.model.GlResourceType;


public class FindAllGlResourceTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlResourceType> returnVal = new ArrayList<GlResourceType>();
try{
List<GenericValue> results = delegator.findAll("GlResourceType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlResourceTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlResourceTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
