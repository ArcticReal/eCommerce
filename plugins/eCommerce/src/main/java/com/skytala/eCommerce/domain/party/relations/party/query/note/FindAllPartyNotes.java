
package com.skytala.eCommerce.domain.party.relations.party.query.note;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.party.event.note.PartyNoteFound;
import com.skytala.eCommerce.domain.party.relations.party.mapper.note.PartyNoteMapper;
import com.skytala.eCommerce.domain.party.relations.party.model.note.PartyNote;


public class FindAllPartyNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<PartyNote> returnVal = new ArrayList<PartyNote>();
try{
List<GenericValue> results = delegator.findAll("PartyNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(PartyNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new PartyNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
