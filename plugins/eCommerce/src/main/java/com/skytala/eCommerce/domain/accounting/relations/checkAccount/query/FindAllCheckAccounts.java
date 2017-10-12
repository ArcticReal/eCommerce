
package com.skytala.eCommerce.domain.accounting.relations.checkAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.event.CheckAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.mapper.CheckAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;


public class FindAllCheckAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CheckAccount> returnVal = new ArrayList<CheckAccount>();
try{
List<GenericValue> results = delegator.findAll("CheckAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CheckAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CheckAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
