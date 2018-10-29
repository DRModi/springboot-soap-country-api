package com.drmodi.learn.springbootsoapcountryapi.exception;




/*
The ServiceFaultException extends from RuntimeException, so we don’t need to catch or add it to our method signature.
With the ServiceFault object we can add some detailed information about the error that occurred.
*/


public class ServiceFaultException extends RuntimeException {

    private ServiceFault serviceFault;

    public ServiceFaultException(String message, ServiceFault serviceFault) {

        super(message);
        this.serviceFault = serviceFault;
    }

    public ServiceFaultException(String message, Throwable e, ServiceFault serviceFault) {
        super(message, e);
        this.serviceFault = serviceFault;


    }

    public ServiceFault getServiceFault() {
        return serviceFault;
    }

    public void setServiceFault(ServiceFault serviceFault) {
        this.serviceFault = serviceFault;
    }
}
