
package com.skytala.eCommerce.domain.product.relations.quantityBreak.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.event.type.QuantityBreakTypeFound;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.mapper.type.QuantityBreakTypeMapper;
import com.skytala.eCommerce.domain.product.relations.quantityBreak.model.type.QuantityBreakType;


public class FindAllQuantityBreakTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuantityBreakType> returnVal = new ArrayList<QuantityBreakType>();
try{
List<GenericValue> results = delegator.findAll("QuantityBreakType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuantityBreakTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuantityBreakTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
