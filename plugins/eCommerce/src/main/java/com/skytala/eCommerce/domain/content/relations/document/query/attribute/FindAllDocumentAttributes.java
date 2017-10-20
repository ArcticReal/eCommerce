
package com.skytala.eCommerce.domain.content.relations.document.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.document.event.attribute.DocumentAttributeFound;
import com.skytala.eCommerce.domain.content.relations.document.mapper.attribute.DocumentAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.attribute.DocumentAttribute;


public class FindAllDocumentAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DocumentAttribute> returnVal = new ArrayList<DocumentAttribute>();
try{
List<GenericValue> results = delegator.findAll("DocumentAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DocumentAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DocumentAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
