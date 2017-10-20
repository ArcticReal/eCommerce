
package com.skytala.eCommerce.domain.accounting.relations.billingAccount.query.role;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.role.BillingAccountRoleFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.role.BillingAccountRoleMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.role.BillingAccountRole;


public class FindAllBillingAccountRoles extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BillingAccountRole> returnVal = new ArrayList<BillingAccountRole>();
try{
List<GenericValue> results = delegator.findAll("BillingAccountRole", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BillingAccountRoleMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BillingAccountRoleFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
