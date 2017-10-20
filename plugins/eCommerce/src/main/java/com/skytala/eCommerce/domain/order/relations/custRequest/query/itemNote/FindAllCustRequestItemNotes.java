
package com.skytala.eCommerce.domain.order.relations.custRequest.query.itemNote;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteFound;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemNote.CustRequestItemNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemNote.CustRequestItemNote;


public class FindAllCustRequestItemNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<CustRequestItemNote> returnVal = new ArrayList<CustRequestItemNote>();
try{
List<GenericValue> results = delegator.findAll("CustRequestItemNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(CustRequestItemNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new CustRequestItemNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
