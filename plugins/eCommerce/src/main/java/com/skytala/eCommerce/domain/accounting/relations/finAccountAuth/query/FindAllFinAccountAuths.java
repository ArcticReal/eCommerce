
package com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.event.FinAccountAuthFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.mapper.FinAccountAuthMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAuth.model.FinAccountAuth;


public class FindAllFinAccountAuths extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountAuth> returnVal = new ArrayList<FinAccountAuth>();
try{
List<GenericValue> results = delegator.findAll("FinAccountAuth", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountAuthMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountAuthFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
