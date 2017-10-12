
package com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.event.GlReconciliationEntryFound;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.mapper.GlReconciliationEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.glReconciliationEntry.model.GlReconciliationEntry;


public class FindAllGlReconciliationEntrys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlReconciliationEntry> returnVal = new ArrayList<GlReconciliationEntry>();
try{
List<GenericValue> results = delegator.findAll("GlReconciliationEntry", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlReconciliationEntryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlReconciliationEntryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
