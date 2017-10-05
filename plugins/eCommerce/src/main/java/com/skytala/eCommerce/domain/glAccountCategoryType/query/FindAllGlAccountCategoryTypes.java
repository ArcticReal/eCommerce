
package com.skytala.eCommerce.domain.glAccountCategoryType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glAccountCategoryType.event.GlAccountCategoryTypeFound;
import com.skytala.eCommerce.domain.glAccountCategoryType.mapper.GlAccountCategoryTypeMapper;
import com.skytala.eCommerce.domain.glAccountCategoryType.model.GlAccountCategoryType;


public class FindAllGlAccountCategoryTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountCategoryType> returnVal = new ArrayList<GlAccountCategoryType>();
try{
List<GenericValue> results = delegator.findAll("GlAccountCategoryType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountCategoryTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountCategoryTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
