
package com.skytala.eCommerce.domain.order.relations.custRequest.query.item;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.item.CustRequestItemFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.item.CustRequestItemMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.item.CustRequestItem;


public class FindAllCustRequestItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestItem> returnVal = new ArrayList<CustRequestItem>();
try{
List<GenericValue> results = delegator.findAll("CustRequestItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
