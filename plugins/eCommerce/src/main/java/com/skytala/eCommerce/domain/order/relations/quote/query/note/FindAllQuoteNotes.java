
package com.skytala.eCommerce.domain.order.relations.quote.query.note;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.quote.event.note.QuoteNoteFound;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.note.QuoteNoteMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;


public class FindAllQuoteNotes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<QuoteNote> returnVal = new ArrayList<QuoteNote>();
try{
List<GenericValue> results = delegator.findAll("QuoteNote", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(QuoteNoteMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new QuoteNoteFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
