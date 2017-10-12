
package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.event.FinAccountStatusFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.mapper.FinAccountStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;


public class FindAllFinAccountStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountStatus> returnVal = new ArrayList<FinAccountStatus>();
try{
List<GenericValue> results = delegator.findAll("FinAccountStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
