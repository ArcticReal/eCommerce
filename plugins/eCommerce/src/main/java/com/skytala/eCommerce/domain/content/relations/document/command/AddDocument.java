package com.skytala.eCommerce.domain.content.relations.document.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.document.event.DocumentAdded;
import com.skytala.eCommerce.domain.content.relations.document.mapper.DocumentMapper;
import com.skytala.eCommerce.domain.content.relations.document.model.Document;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDocument extends Command {

private Document elementToBeAdded;
public AddDocument(Document elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

Document addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDocumentId(delegator.getNextSeqId("Document"));
GenericValue newValue = delegator.makeValue("Document", elementToBeAdded.mapAttributeField());
addedElement = DocumentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DocumentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
