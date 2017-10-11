
package com.skytala.eCommerce.domain.product.relations.varianceReason.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.varianceReason.event.VarianceReasonFound;
import com.skytala.eCommerce.domain.product.relations.varianceReason.mapper.VarianceReasonMapper;
import com.skytala.eCommerce.domain.product.relations.varianceReason.model.VarianceReason;


public class FindAllVarianceReasons extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<VarianceReason> returnVal = new ArrayList<VarianceReason>();
try{
List<GenericValue> results = delegator.findAll("VarianceReason", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(VarianceReasonMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new VarianceReasonFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
