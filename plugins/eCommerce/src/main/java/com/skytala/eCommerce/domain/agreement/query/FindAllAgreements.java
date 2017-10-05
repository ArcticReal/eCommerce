
package com.skytala.eCommerce.domain.agreement.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.agreement.event.AgreementFound;
import com.skytala.eCommerce.domain.agreement.mapper.AgreementMapper;
import com.skytala.eCommerce.domain.agreement.model.Agreement;


public class FindAllAgreements extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<Agreement> returnVal = new ArrayList<Agreement>();
try{
List<GenericValue> results = delegator.findAll("Agreement", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AgreementMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AgreementFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
