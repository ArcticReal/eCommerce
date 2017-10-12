
package com.skytala.eCommerce.domain.accounting.relations.eftAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountFound;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.mapper.EftAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;


public class FindAllEftAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EftAccount> returnVal = new ArrayList<EftAccount>();
try{
List<GenericValue> results = delegator.findAll("EftAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EftAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EftAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
