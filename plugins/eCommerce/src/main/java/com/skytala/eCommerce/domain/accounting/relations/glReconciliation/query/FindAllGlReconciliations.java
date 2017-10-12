
package com.skytala.eCommerce.domain.accounting.relations.glReconciliation.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.event.GlReconciliationFound;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.mapper.GlReconciliationMapper;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliation.model.GlReconciliation;


public class FindAllGlReconciliations extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlReconciliation> returnVal = new ArrayList<GlReconciliation>();
try{
List<GenericValue> results = delegator.findAll("GlReconciliation", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlReconciliationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlReconciliationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
