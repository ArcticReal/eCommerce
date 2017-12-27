package com.skytala.eCommerce.domain.order;


import com.skytala.eCommerce.domain.cart.Position;
import com.skytala.eCommerce.domain.cart.ShoppingCart;
import com.skytala.eCommerce.domain.cart.dto.ShoppingCartItemDTO;
import com.skytala.eCommerce.domain.order.dto.ContactDTO;
import com.skytala.eCommerce.domain.order.dto.OrderDetailsDTO;
import com.skytala.eCommerce.domain.order.dto.OrderListItemDTO;
import com.skytala.eCommerce.domain.order.relations.orderHeader.OrderHeaderController;
import com.skytala.eCommerce.domain.order.relations.orderHeader.model.OrderHeader;
import com.skytala.eCommerce.domain.order.relations.orderItem.OrderItemController;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.OrderItem;
import com.skytala.eCommerce.domain.order.util.OrderStatusUtil;
import com.skytala.eCommerce.domain.party.PartyController;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.domain.party.relations.contactMech.ContactMechController;
import com.skytala.eCommerce.domain.party.relations.contactMech.control.party.PartyContactMechController;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.util.ContactMechTypes;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.domain.party.relations.postalAddress.PostalAddressController;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.domain.product.ProductController;
import com.skytala.eCommerce.domain.shipment.ShipmentController;
import com.skytala.eCommerce.domain.shipment.model.Shipment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.util.TimestampUtil;
import com.skytala.eCommerce.service.login.LoginServicesController;
import org.apache.ofbiz.base.util.UtilMisc;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.*;
import static com.skytala.eCommerce.service.login.util.SecurityGroups.*;


@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    PartyController partyController;

    @Resource
    ProductController productController;

    @Resource
    ShipmentController shipmentController;

    @Resource
    OrderHeaderController orderHeaderController;

    @Resource
    OrderItemController orderItemController;

    @Resource
    ContactMechController contactMechController;

    @Resource
    PartyContactMechController partyContactMechController;

    @Resource
    PostalAddressController postalAddressController;

    @Resource
    private LoginServicesController loginServicesController;


    @GetMapping("/listOwn")
    @PreAuthorize(HAS_USER_AUTHORITY)
    public ResponseEntity<List<OrderListItemDTO>> getUserOrderList(Principal principal) throws Exception {

        Person person = loginServicesController.getLoggedInPerson(principal).getBody().extractPerson();

        Map<String, String> filter = UtilMisc.toMap("partyIdTo", person.getPartyId());

        List<Shipment> shipments = shipmentController.findShipmentsBy(filter).getBody();

        List<OrderHeader> headers = getOrderHeaders(shipments, principal);

        List<OrderListItemDTO> result =
                headers.stream()
                        .sorted((header1, header2) -> header2.getOrderDate().compareTo(header1.getOrderDate()))
                        .map(OrderListItemDTO::new)
                        .collect(Collectors.toList());

        return successful(result);
    }

    @GetMapping("/listAll")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity<List<OrderListItemDTO>> getAllOrdersAsList(Principal principal) throws Exception {

        List<Shipment> shipments = shipmentController.findShipmentsBy(null).getBody();
        return successful(getOrderHeaders(shipments, principal)
                .stream()
                .sorted((header1, header2) -> header1.getOrderDate().compareTo(header2.getOrderDate()))
                .map(OrderListItemDTO::new)
                .collect(Collectors.toList()));

    }

    private List<OrderHeader> getOrderHeaders(List<Shipment> shipments, Principal principal) {

        List<OrderHeader> headers = shipments.stream().map(shipment -> {
            try {
                return orderHeaderController.findById(shipment.getPrimaryOrderId(), principal).getBody();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        })
        .collect(Collectors.toList());


        while (headers.remove(null));
        return headers;
    }

    @GetMapping("/{orderHeaderId}/details")
    @PreAuthorize(HAS_USER_AUTHORITY+" or " + HAS_ADMIN_AUTHORITY)
    public ResponseEntity<OrderDetailsDTO> getOrderDetails(Principal principal,
                                                           @PathVariable("orderHeaderId") String orderHeaderId) throws Exception {


        // check if order was made by current user
        try{
            if(!(checkOrderForAuthorisation(principal, orderHeaderId)||isAdmin(principal)))
                return unauthorized();
        }catch(RecordNotFoundException e){
            return serverError();
        }

        OrderHeader header = orderHeaderController.findById(orderHeaderId, principal).getBody();

        List<Shipment> shipments = shipmentController.findShipmentsBy(UtilMisc.toMap("primaryOrderId", orderHeaderId)).getBody();
        Party party = null;

        if(shipments.size()==1)
            party = partyController.findById(shipments.get(0).getPartyIdTo()).getBody();
        else
            return serverError();

        List<ContactMech> contactMechs = new LinkedList<>();
        PostalAddress address = null;
        ContactMech eMail = new ContactMech();

        if(party!=null) {

            List<PartyContactMech> partyContactMechs = partyContactMechController.findPartyContactMechsBy(UtilMisc.toMap("partyId", party.getPartyId()))
                    .getBody();

            List<String> contactMechIds = partyContactMechs.stream()
                    .map(partyContactMech -> {
                        return partyContactMech.getContactMechId();
                    }).collect(Collectors.toList());

            contactMechs = contactMechIds.stream().map(contactMechId -> {
                try {
                    ContactMech contactMech = contactMechController.findById(contactMechId).getBody();
                    if (contactMech.getContactMechTypeId().equals("POSTAL_ADDRESS")||contactMech.getContactMechTypeId().equals("EMAIL_ADDRESS"))
                        return contactMech;
                    else
                        return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList());

            while (contactMechs.remove(null));

            if(contactMechs.size()<1)
                return badRequest();

            for(ContactMech mech : contactMechs){
                if(mech.getContactMechTypeId().equals("EMAIL_ADDRESS")){

                    eMail = mech;
                    mech = null;
                }
            }

            while (contactMechs.remove(null));

            if(shipments.get(0).getDestinationContactMechId()==null)
                address = postalAddressController.findById(contactMechs.get(0).getContactMechId()).getBody();
            else{

                String contactMechId = shipments.get(0).getDestinationContactMechId();
                if(contactMechId != null)
                    address = postalAddressController.findById(contactMechId).getBody();
                else
                    return badRequest();

            }

        }else{
            String contactMechId = shipments.get(0).getDestinationContactMechId();
            if(contactMechId != null)
                address = postalAddressController.findById(contactMechId).getBody();
            else
                return badRequest();
        }

        List<OrderItem> items = orderItemController.findOrderItemsBy(UtilMisc.toMap("orderId", header.getOrderId()))
                                                   .getBody();

        List<ShoppingCartItemDTO> products = new LinkedList<>();

        for(OrderItem t : items){
            ShoppingCartItemDTO dto = ShoppingCartItemDTO
                    .create(productController.findByIdWithDetails(t.getProductId()).getBody(),
                            t.getQuantity());
            dto.setPrice(t.getUnitPrice());
            products.add(dto);
        }

        return successful(OrderDetailsDTO.create(header, address, eMail, products, header.getGrandTotal()));
    }




    @PostMapping("/finish")
    @PreAuthorize(HAS_USER_AUTHORITY)
    public ResponseEntity<OrderDetailsDTO> finishOrder(HttpSession session,
                                                       Principal principal,
                                                       @RequestBody ContactDTO contact) throws Exception {


        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if(cart == null)
            return badRequest();


        //set general orderheader attributes
        OrderHeader headerToBeAdded = new OrderHeader();
        headerToBeAdded.setCurrencyUom("EUR");
        headerToBeAdded.setOrderDate(TimestampUtil.currentTime());
        headerToBeAdded.setGrandTotal(cart.getGrandTotal());
        headerToBeAdded.setOrderTypeId("SALES_ORDER");
        headerToBeAdded.setProductStoreId("SKYTALA_DIETMANNSR.");
        headerToBeAdded.setStatusId("ORDER_PROCESSING");
        headerToBeAdded.setSalesChannelEnumId("WEB_SALES_CHANNEL");

        OrderHeader addedHeader = orderHeaderController.createOrderHeader(headerToBeAdded).getBody();
        if(addedHeader == null)
            return conflict();
        String orderId = addedHeader.getOrderId();


        //Set general orderItem attributes
        OrderItem itemToBeCreated = new OrderItem();
        itemToBeCreated.setOrderId(orderId);
        itemToBeCreated.setOrderItemTypeId("PRODUCT_ORDER_ITEM");

        List<ShoppingCartItemDTO> products = new LinkedList<>();

        for(Position p : cart.getPositions()){
            ShoppingCartItemDTO dto = ShoppingCartItemDTO
                    .create(productController.findByIdWithDetails(p.getProduct()
                                                                   .getProductId())
                                             .getBody(),
                            p.getNumberProducts());
            itemToBeCreated.setProductId(p.getProduct().getProductId());
            itemToBeCreated.setQuantity(p.getNumberProducts());
            itemToBeCreated.setUnitPrice(dto.getPrice());
            orderItemController.createOrderItem(itemToBeCreated);
            products.add(dto);
        }


        Person person = loginServicesController.getLoggedInPerson(principal).getBody().extractPerson();

        ContactMech addressMech = contactMechController.getPostalAddressFor(person.getPartyId());
        String contactMechId;
        if(addressMech==null){
            //no unique address found
            PostalAddress address = contact.extractPostalAddress();
            if(address.isValid()){
                if(address.getContactMechId()!=null)
                    contactMechId = address.getContactMechId();
                else{
                    ContactMech mech = new ContactMech();
                    mech.setContactMechTypeId(ContactMechTypes.POSTAL_ADDRESS);
                    mech = contactMechController.createContactMech(mech).getBody();
                    contactMechId = mech.getContactMechId();
                    address.setContactMechId(contactMechId);
                    postalAddressController.createPostalAddress(address);
                }
            }else
                throw new IllegalArgumentException("address error");
        }
        else
            contactMechId = addressMech.getContactMechId();

        PostalAddress address = postalAddressController.findById(contactMechId).getBody();
        ContactMech eMailContactMech = contactMechController.getEmailAddressFor(person.getPartyId());

        Shipment shipmentToBeAdded = new Shipment();
        shipmentToBeAdded.setPrimaryOrderId(orderId);
        shipmentToBeAdded.setCurrencyUomId("EUR");
        shipmentToBeAdded.setPartyIdTo(person.getPartyId());
        shipmentToBeAdded.setDestinationContactMechId(address.getContactMechId());
        shipmentController.createShipment(shipmentToBeAdded);


        session.setAttribute("cart", null);

        return successful(OrderDetailsDTO.create(addedHeader, address, eMailContactMech, products, cart.getGrandTotal()));
    }

    @PutMapping("/{orderId}/cancel")
    @PreAuthorize(HAS_USER_AUTHORITY)
    public ResponseEntity<OrderHeader> cancelOrder(Principal principal, @PathVariable("orderId") String orderId) throws Exception {


        // check if the order is made by current user
        try {
            if(!checkOrderForAuthorisation(principal, orderId))
                return unauthorized();
        }catch(RecordNotFoundException e){
            return serverError();
        }

        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.PROCESSING)||status.equals(OrderStatusUtil.HOLD)
            ||status.equals(OrderStatusUtil.APPROVED))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.CANCELLED);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());
    }

    @PutMapping("/{orderId}/approve")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity<OrderHeader> approveOrder(Principal principal , @PathVariable("orderId") String orderId) throws Exception {


        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.PROCESSING)||status.equals(OrderStatusUtil.HOLD))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.APPROVED);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());

    }


    @PutMapping("/{orderId}/reject")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity<OrderHeader> rejectOrder(Principal principal , @PathVariable("orderId") String orderId) throws Exception {

        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.PROCESSING)||status.equals(OrderStatusUtil.HOLD))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.REJECTED);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());

    }

    @PutMapping("/{orderId}/hold")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity<OrderHeader> holdOrder(Principal principal , @PathVariable("orderId") String orderId) throws Exception {

        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.PROCESSING))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.HOLD);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());

    }

    @PutMapping("/{orderId}/create")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity<OrderHeader> createOrder(Principal principal , @PathVariable("orderId") String orderId) throws Exception {


        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.APPROVED))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.CREATED);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());

    }

    @PutMapping("/{orderId}/send")
    @PreAuthorize(HAS_ADMIN_AUTHORITY)
    public ResponseEntity<OrderHeader> sendOrder(Principal principal , @PathVariable("orderId") String orderId) throws Exception {


        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.CREATED))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.SENT);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());

    }

    @PutMapping("/{orderId}/complete")
    @PreAuthorize(HAS_ADMIN_AUTHORITY + " or " + HAS_USER_AUTHORITY)
    public ResponseEntity<OrderHeader> completeOrder(Principal principal , @PathVariable("orderId") String orderId) throws Exception {


        OrderHeader header = orderHeaderController.findById(orderId, principal).getBody();

        String status = header.getStatusId();
        if(!(status.equals(OrderStatusUtil.SENT))){
            return badRequest();
        }

        header.setStatusId(OrderStatusUtil.COMPLETED);

        return orderHeaderController.updateOrderHeader(header, header.getOrderId());

    }

    private boolean checkOrderForAuthorisation(Principal principal, String orderId) throws Exception {

        List<Shipment> shipments = shipmentController.findShipmentsBy(UtilMisc.toMap("primaryOrderId", orderId)).getBody();
        Party party = null;

        if(shipments.size()==1){

            party = partyController.findById(shipments.get(0).getPartyIdTo()).getBody();

            Person person = loginServicesController.getLoggedInPerson(principal).getBody().extractPerson();
            if(person == null || !party.getPartyId().equals(person.getPartyId()))
                return false;

        }else
            throw new RecordNotFoundException(Shipment.class);

        return true;
    }

    private boolean isAdmin(Principal principal) throws Exception {
        return loginServicesController.getLoggedInPerson(principal)
                .getBody()
                .getAuthorities()
                .contains(ADMIN) ? true : false;

    }

}