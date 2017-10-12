
package com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.event.FinAccountTransTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.mapper.FinAccountTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccountTransTypeAttr.model.FinAccountTransTypeAttr;


public class FindAllFinAccountTransTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTransTypeAttr> returnVal = new ArrayList<FinAccountTransTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("FinAccountTransTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTransTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTransTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
