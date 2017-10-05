
package com.skytala.eCommerce.domain.custRequestStatus.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.custRequestStatus.event.CustRequestStatusFound;
import com.skytala.eCommerce.domain.custRequestStatus.mapper.CustRequestStatusMapper;
import com.skytala.eCommerce.domain.custRequestStatus.model.CustRequestStatus;


public class FindAllCustRequestStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestStatus> returnVal = new ArrayList<CustRequestStatus>();
try{
List<GenericValue> results = delegator.findAll("CustRequestStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
