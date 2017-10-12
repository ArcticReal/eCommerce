
package com.skytala.eCommerce.domain.accounting.relations.billingAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.event.BillingAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.mapper.BillingAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccount.model.BillingAccount;


public class FindAllBillingAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BillingAccount> returnVal = new ArrayList<BillingAccount>();
try{
List<GenericValue> results = delegator.findAll("BillingAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BillingAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BillingAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
