
package com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.event.BillingAccountTermAttrFound;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.mapper.BillingAccountTermAttrMapper;
import com.skytala.eCommerce.domain.accounting.relations.billingAccountTermAttr.model.BillingAccountTermAttr;


public class FindAllBillingAccountTermAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<BillingAccountTermAttr> returnVal = new ArrayList<BillingAccountTermAttr>();
try{
List<GenericValue> results = delegator.findAll("BillingAccountTermAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(BillingAccountTermAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new BillingAccountTermAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
