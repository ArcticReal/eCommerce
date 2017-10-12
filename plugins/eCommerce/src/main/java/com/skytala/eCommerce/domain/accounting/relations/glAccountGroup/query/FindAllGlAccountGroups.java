
package com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.event.GlAccountGroupFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.mapper.GlAccountGroupMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountGroup.model.GlAccountGroup;


public class FindAllGlAccountGroups extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountGroup> returnVal = new ArrayList<GlAccountGroup>();
try{
List<GenericValue> results = delegator.findAll("GlAccountGroup", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountGroupMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountGroupFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
