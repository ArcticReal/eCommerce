
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.FinAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;


public class FindAllFinAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccount> returnVal = new ArrayList<FinAccount>();
try{
List<GenericValue> results = delegator.findAll("FinAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
