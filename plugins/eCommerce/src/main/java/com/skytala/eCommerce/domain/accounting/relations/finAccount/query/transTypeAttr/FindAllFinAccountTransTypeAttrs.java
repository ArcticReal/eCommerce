
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.transTypeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.transTypeAttr.FinAccountTransTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.transTypeAttr.FinAccountTransTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.transTypeAttr.FinAccountTransTypeAttr;


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
