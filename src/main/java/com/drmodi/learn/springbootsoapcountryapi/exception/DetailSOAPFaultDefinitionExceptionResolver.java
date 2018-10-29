package com.drmodi.learn.springbootsoapcountryapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;



/*
The DetailSoapFaultDefinitionExceptionResolver extends from the SoapFaultMappingExceptionResolver and
is used for enhancing the SoapFault with detailed information about the error that occurred.
We can override the customizeFault method to enhance the exception with detail information.
We do this by calling the addFaultDetail method of the SoapFault class, and adding QName indicating the element with the addFaultDetailElement.
*/

@Slf4j
public class DetailSOAPFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    private static final QName _CODE = new QName("code");
    private static final QName _DESCRIPTION = new QName("description");


    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault){

       //log.warn("Exception Processed : ", ex);

       if(ex instanceof ServiceFaultException){
           ServiceFault serviceFault = ((ServiceFaultException) ex).getServiceFault();

           SoapFaultDetail detail = fault.addFaultDetail();

           detail.addFaultDetailElement(_CODE).addText(serviceFault.getCode());
           detail.addFaultDetailElement(_DESCRIPTION).addText(serviceFault.getDescription());
       }
    }
}
