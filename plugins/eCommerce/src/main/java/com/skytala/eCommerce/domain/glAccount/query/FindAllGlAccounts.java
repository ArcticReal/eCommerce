
package com.skytala.eCommerce.domain.glAccount.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glAccount.event.GlAccountFound;
import com.skytala.eCommerce.domain.glAccount.mapper.GlAccountMapper;
import com.skytala.eCommerce.domain.glAccount.model.GlAccount;


public class FindAllGlAccounts extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlAccount> returnVal = new ArrayList<GlAccount>();
try{
List<GenericValue> results = delegator.findAll("GlAccount", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlAccountMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlAccountFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
