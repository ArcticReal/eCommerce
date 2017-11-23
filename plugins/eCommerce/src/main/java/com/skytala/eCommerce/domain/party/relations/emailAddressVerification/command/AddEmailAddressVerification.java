package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event.EmailAddressVerificationAdded;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.mapper.EmailAddressVerificationMapper;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmailAddressVerification extends Command {

    private EmailAddressVerification elementToBeAdded;
    public AddEmailAddressVerification(EmailAddressVerification elementToBeAdded){
        this.elementToBeAdded = elementToBeAdded;
    }

    @Override
    public Event execute(){


        Delegator delegator = DelegatorFactory.getDelegator("default");

        EmailAddressVerification addedElement = null;
        boolean success = false;
        try {


            GenericValue newValue = delegator.makeValue("EmailAddressVerification", elementToBeAdded.mapAttributeField());
            addedElement = EmailAddressVerificationMapper.map(delegator.create(newValue));
            success = true;
        } catch(GenericEntityException e) {
            e.printStackTrace();
            addedElement = null;
        }

        Event resultingEvent = new EmailAddressVerificationAdded(addedElement, success);
        Broker.instance().publish(resultingEvent);
        return resultingEvent;
    }
}
