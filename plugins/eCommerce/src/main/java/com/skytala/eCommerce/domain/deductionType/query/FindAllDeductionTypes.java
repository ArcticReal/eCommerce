
package com.skytala.eCommerce.domain.deductionType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.deductionType.event.DeductionTypeFound;
import com.skytala.eCommerce.domain.deductionType.mapper.DeductionTypeMapper;
import com.skytala.eCommerce.domain.deductionType.model.DeductionType;


public class FindAllDeductionTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DeductionType> returnVal = new ArrayList<DeductionType>();
try{
List<GenericValue> results = delegator.findAll("DeductionType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DeductionTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DeductionTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
