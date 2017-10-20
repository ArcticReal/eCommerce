
package com.skytala.eCommerce.domain.content.relations.document.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.document.event.typeAttr.DocumentTypeAttrFound;
import com.skytala.eCommerce.domain.content.relations.document.mapper.typeAttr.DocumentTypeAttrMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.typeAttr.DocumentTypeAttr;


public class FindAllDocumentTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DocumentTypeAttr> returnVal = new ArrayList<DocumentTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("DocumentTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DocumentTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DocumentTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
