
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transType.FinAccountTransTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transType.FinAccountTransTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transType.FinAccountTransType;


public class FindAllFinAccountTransTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTransType> returnVal = new ArrayList<FinAccountTransType>();
try{
List<GenericValue> results = delegator.findAll("FinAccountTransType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTransTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTransTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
