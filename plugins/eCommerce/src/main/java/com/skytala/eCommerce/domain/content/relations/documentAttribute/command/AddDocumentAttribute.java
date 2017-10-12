package com.skytala.eCommerce.domain.content.relations.documentAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.documentAttribute.event.DocumentAttributeAdded;
import com.skytala.eCommerce.domain.content.relations.documentAttribute.mapper.DocumentAttributeMapper;
import com.skytala.eCommerce.domain.content.relations.documentAttribute.model.DocumentAttribute;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDocumentAttribute extends Command {

private DocumentAttribute elementToBeAdded;
public AddDocumentAttribute(DocumentAttribute elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DocumentAttribute addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("DocumentAttribute", elementToBeAdded.mapAttributeField());
addedElement = DocumentAttributeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DocumentAttributeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
