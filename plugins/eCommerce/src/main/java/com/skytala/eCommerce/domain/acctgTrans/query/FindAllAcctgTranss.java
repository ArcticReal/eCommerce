
package com.skytala.eCommerce.domain.acctgTrans.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.acctgTrans.event.AcctgTransFound;
import com.skytala.eCommerce.domain.acctgTrans.mapper.AcctgTransMapper;
import com.skytala.eCommerce.domain.acctgTrans.model.AcctgTrans;


public class FindAllAcctgTranss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AcctgTrans> returnVal = new ArrayList<AcctgTrans>();
try{
List<GenericValue> results = delegator.findAll("AcctgTrans", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AcctgTransMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AcctgTransFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
