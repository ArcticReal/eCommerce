
package com.skytala.eCommerce.domain.picklistBin.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.picklistBin.event.PicklistBinFound;
import com.skytala.eCommerce.domain.picklistBin.mapper.PicklistBinMapper;
import com.skytala.eCommerce.domain.picklistBin.model.PicklistBin;


public class FindAllPicklistBins extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PicklistBin> returnVal = new ArrayList<PicklistBin>();
try{
List<GenericValue> results = delegator.findAll("PicklistBin", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PicklistBinMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PicklistBinFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
