
package com.skytala.eCommerce.domain.glAccountGroupType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glAccountGroupType.event.GlAccountGroupTypeFound;
import com.skytala.eCommerce.domain.glAccountGroupType.mapper.GlAccountGroupTypeMapper;
import com.skytala.eCommerce.domain.glAccountGroupType.model.GlAccountGroupType;


public class FindAllGlAccountGroupTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountGroupType> returnVal = new ArrayList<GlAccountGroupType>();
try{
List<GenericValue> results = delegator.findAll("GlAccountGroupType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountGroupTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountGroupTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
