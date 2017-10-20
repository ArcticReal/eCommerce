
package com.skytala.eCommerce.domain.order.relations.custRequest.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.attribute.CustRequestAttributeMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;


public class FindAllCustRequestAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestAttribute> returnVal = new ArrayList<CustRequestAttribute>();
try{
List<GenericValue> results = delegator.findAll("CustRequestAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
