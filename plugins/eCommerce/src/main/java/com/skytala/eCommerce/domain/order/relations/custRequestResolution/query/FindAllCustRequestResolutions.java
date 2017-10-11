
package com.skytala.eCommerce.domain.order.relations.custRequestResolution.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequestResolution.event.CustRequestResolutionFound;
import com.skytala.eCommerce.domain.order.relations.custRequestResolution.mapper.CustRequestResolutionMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestResolution.model.CustRequestResolution;


public class FindAllCustRequestResolutions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestResolution> returnVal = new ArrayList<CustRequestResolution>();
try{
List<GenericValue> results = delegator.findAll("CustRequestResolution", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestResolutionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestResolutionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
