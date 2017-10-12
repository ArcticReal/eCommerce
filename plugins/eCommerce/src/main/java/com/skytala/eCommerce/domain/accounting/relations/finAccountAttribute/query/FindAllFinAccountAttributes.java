
package com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.event.FinAccountAttributeFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.mapper.FinAccountAttributeMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountAttribute.model.FinAccountAttribute;


public class FindAllFinAccountAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountAttribute> returnVal = new ArrayList<FinAccountAttribute>();
try{
List<GenericValue> results = delegator.findAll("FinAccountAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
