
package com.skytala.eCommerce.domain.accounting.relations.finAccount.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.typeAttr.FinAccountTypeAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.typeAttr.FinAccountTypeAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.typeAttr.FinAccountTypeAttr;


public class FindAllFinAccountTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<FinAccountTypeAttr> returnVal = new ArrayList<FinAccountTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("FinAccountTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(FinAccountTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new FinAccountTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
