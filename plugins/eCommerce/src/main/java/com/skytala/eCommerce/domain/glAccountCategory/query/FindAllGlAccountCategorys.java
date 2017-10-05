
package com.skytala.eCommerce.domain.glAccountCategory.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glAccountCategory.event.GlAccountCategoryFound;
import com.skytala.eCommerce.domain.glAccountCategory.mapper.GlAccountCategoryMapper;
import com.skytala.eCommerce.domain.glAccountCategory.model.GlAccountCategory;


public class FindAllGlAccountCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountCategory> returnVal = new ArrayList<GlAccountCategory>();
try{
List<GenericValue> results = delegator.findAll("GlAccountCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
