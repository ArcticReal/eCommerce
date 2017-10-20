
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.typeGlAccount;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeGlAccount.FinAccountTypeGlAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.typeGlAccount.FinAccountTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeGlAccount.FinAccountTypeGlAccount;


public class FindAllFinAccountTypeGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTypeGlAccount> returnVal = new ArrayList<FinAccountTypeGlAccount>();
try{
List<GenericValue> results = delegator.findAll("FinAccountTypeGlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTypeGlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTypeGlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
