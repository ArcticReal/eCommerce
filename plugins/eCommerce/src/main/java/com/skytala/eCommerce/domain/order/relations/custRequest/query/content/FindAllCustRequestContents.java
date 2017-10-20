
package com.skytala.eCommerce.domain.order.relations.custRequest.query.content;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.content.CustRequestContentFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.content.CustRequestContentMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.content.CustRequestContent;


public class FindAllCustRequestContents extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestContent> returnVal = new ArrayList<CustRequestContent>();
try{
List<GenericValue> results = delegator.findAll("CustRequestContent", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestContentMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestContentFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
