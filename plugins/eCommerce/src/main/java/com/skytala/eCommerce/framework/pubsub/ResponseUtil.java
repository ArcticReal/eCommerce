package com.skytala.eCommerce.framework.pubsub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity successful(){return ResponseEntity.ok(null);}
    public static ResponseEntity successful(Object responseObject){return ResponseEntity.ok(responseObject);}

    public static ResponseEntity badRequest(){return ResponseEntity.badRequest().body(null);}
    public static ResponseEntity badRequest(Object responseObject){return ResponseEntity.badRequest().body(responseObject);}

    public static ResponseEntity conflict(){return ResponseEntity.status(HttpStatus.CONFLICT).body(null);}
    public static ResponseEntity conflict(Object responseObject){return ResponseEntity.status(HttpStatus.CONFLICT).body(responseObject);}

    public static ResponseEntity serverError(){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);}
    public static ResponseEntity serverError(Object responseObject){return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);}

    public static ResponseEntity noContent(){return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);}

    public static ResponseEntity unauthorized(){return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);}

    public static ResponseEntity notFound(){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}
}
