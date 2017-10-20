
package com.skytala.eCommerce.domain.order.relations.custRequest.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.typeAttr.CustRequestTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.typeAttr.CustRequestTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.typeAttr.CustRequestTypeAttr;


public class FindAllCustRequestTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestTypeAttr> returnVal = new ArrayList<CustRequestTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("CustRequestTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
