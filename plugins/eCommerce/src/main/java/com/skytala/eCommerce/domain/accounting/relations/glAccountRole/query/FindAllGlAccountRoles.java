
package com.skytala.eCommerce.domain.accounting.relations.glAccountRole.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.event.GlAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.mapper.GlAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.glAccountRole.model.GlAccountRole;


public class FindAllGlAccountRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccountRole> returnVal = new ArrayList<GlAccountRole>();
try{
List<GenericValue> results = delegator.findAll("GlAccountRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
