
package com.skytala.eCommerce.domain.glJournal.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.glJournal.event.GlJournalFound;
import com.skytala.eCommerce.domain.glJournal.mapper.GlJournalMapper;
import com.skytala.eCommerce.domain.glJournal.model.GlJournal;


public class FindAllGlJournals extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<GlJournal> returnVal = new ArrayList<GlJournal>();
try{
List<GenericValue> results = delegator.findAll("GlJournal", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(GlJournalMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new GlJournalFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
