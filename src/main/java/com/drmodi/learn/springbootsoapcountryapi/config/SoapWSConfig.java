package com.drmodi.learn.springbootsoapcountryapi.config;

import com.drmodi.learn.springbootsoapcountryapi.exception.DetailSOAPFaultDefinitionExceptionResolver;
import com.drmodi.learn.springbootsoapcountryapi.exception.ServiceFaultException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@EnableWs
@Configuration
public class SoapWSConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){

        /* MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/soapWS/*");*/

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soap-ws/*");
    }

/* For the soap exceptions to be propagated properly we must register our SoapFaultMappingExceptionResolver.
   We can define a default SoapFaultDefinition. This default is used when the SoapFaultMappingExceptionResolver
   does not have any appropriate mappings to handle the exception. We can map our Custom Exception by setting
   the mapping properties to the exception resolver. We do this by providing the fully qualified
   class name of the exception as the key and SoapFaultDefinition.SERVER as the value. Finally,
   we have to specify an order because otherwise for some reason the mappings will not work.
    }*/


    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver(){

        SoapFaultMappingExceptionResolver exceptionResolver = new DetailSOAPFaultDefinitionExceptionResolver();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        errorMappings.setProperty(ServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());

        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }



    @Bean
    public XsdSchema countrySchema(){
        return new SimpleXsdSchema(new ClassPathResource("country.xsd"));
    }


    @Bean (name = "country")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countrySchema){

        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setSchema(countrySchema);
        wsdl11Definition.setLocationUri("/soap-ws");
        wsdl11Definition.setPortTypeName("CountryServicePort");
        wsdl11Definition.setTargetNamespace("http://drmodi.learn.com/model/country");
        return wsdl11Definition;

    }

}
