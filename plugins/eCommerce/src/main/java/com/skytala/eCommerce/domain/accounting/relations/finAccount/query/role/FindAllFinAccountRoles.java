
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.role.FinAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.role.FinAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.role.FinAccountRole;


public class FindAllFinAccountRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountRole> returnVal = new ArrayList<FinAccountRole>();
try{
List<GenericValue> results = delegator.findAll("FinAccountRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
