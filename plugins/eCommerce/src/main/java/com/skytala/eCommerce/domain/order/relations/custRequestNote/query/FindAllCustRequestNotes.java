
package com.skytala.eCommerce.domain.order.relations.custRequestNote.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequestNote.event.CustRequestNoteFound;
import com.skytala.eCommerce.domain.order.relations.custRequestNote.mapper.CustRequestNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestNote.model.CustRequestNote;


public class FindAllCustRequestNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestNote> returnVal = new ArrayList<CustRequestNote>();
try{
List<GenericValue> results = delegator.findAll("CustRequestNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
