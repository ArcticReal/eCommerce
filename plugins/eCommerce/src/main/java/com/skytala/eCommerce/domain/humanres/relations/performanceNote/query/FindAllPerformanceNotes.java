
package com.skytala.eCommerce.domain.humanres.relations.performanceNote.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteFound;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.mapper.PerformanceNoteMapper;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;


public class FindAllPerformanceNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PerformanceNote> returnVal = new ArrayList<PerformanceNote>();
try{
List<GenericValue> results = delegator.findAll("PerformanceNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PerformanceNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PerformanceNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
