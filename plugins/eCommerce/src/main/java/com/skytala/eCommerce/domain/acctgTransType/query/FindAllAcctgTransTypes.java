
package com.skytala.eCommerce.domain.acctgTransType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.acctgTransType.event.AcctgTransTypeFound;
import com.skytala.eCommerce.domain.acctgTransType.mapper.AcctgTransTypeMapper;
import com.skytala.eCommerce.domain.acctgTransType.model.AcctgTransType;


public class FindAllAcctgTransTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AcctgTransType> returnVal = new ArrayList<AcctgTransType>();
try{
List<GenericValue> results = delegator.findAll("AcctgTransType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AcctgTransTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AcctgTransTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
