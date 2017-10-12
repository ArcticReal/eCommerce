
package com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.event.AcctgTransEntryFound;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.mapper.AcctgTransEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTransEntry.model.AcctgTransEntry;


public class FindAllAcctgTransEntrys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<AcctgTransEntry> returnVal = new ArrayList<AcctgTransEntry>();
try{
List<GenericValue> results = delegator.findAll("AcctgTransEntry", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(AcctgTransEntryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new AcctgTransEntryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
