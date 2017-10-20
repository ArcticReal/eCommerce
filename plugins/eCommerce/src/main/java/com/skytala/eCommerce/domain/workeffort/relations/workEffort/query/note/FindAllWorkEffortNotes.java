
package com.skytala.eCommerce.domain.workeffort.relations.workEffort.query.note;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.note.WorkEffortNoteFound;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.note.WorkEffortNoteMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.note.WorkEffortNote;


public class FindAllWorkEffortNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<WorkEffortNote> returnVal = new ArrayList<WorkEffortNote>();
try{
List<GenericValue> results = delegator.findAll("WorkEffortNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(WorkEffortNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new WorkEffortNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
