
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.type.FinAccountTypeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.type.FinAccountTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.type.FinAccountType;


public class FindAllFinAccountTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountType> returnVal = new ArrayList<FinAccountType>();
try{
List<GenericValue> results = delegator.findAll("FinAccountType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
