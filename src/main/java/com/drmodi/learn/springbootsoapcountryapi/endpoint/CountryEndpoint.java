package com.drmodi.learn.springbootsoapcountryapi.endpoint;


import com.drmodi.learn.springbootsoapcountryapi.exception.ServiceFault;
import com.drmodi.learn.springbootsoapcountryapi.exception.ServiceFaultException;
import com.drmodi.learn.springbootsoapcountryapi.service.CountryService;
import com.learn.drmodi.model.country.Country;
import com.learn.drmodi.model.country.GetCountryRequest;
import com.learn.drmodi.model.country.GetCountryResponse;
import com.learn.drmodi.model.country.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    @Autowired
    private CountryService countryService;



    //Define Payload Root namespace from which data will be retrieved..like country, request, response object
    //LocalPart - type of request (name of the request payload)
    @PayloadRoot(namespace = "http://drmodi.learn.com/model/country",
            localPart = "getCountryRequest")

    @ResponsePayload //Since this would generate/convert the response payload
    public GetCountryResponse getCountryRequestPayload(@RequestPayload GetCountryRequest request) throws ServiceFaultException {


        Country retrievedCountry = countryService.getCountryDeatails(request.getCountryCode().toLowerCase());

        //There are two ways to create the Response object as displayed below response and response1


        ObjectFactory factory = new ObjectFactory();
        GetCountryResponse response1 = factory.createGetCountryResponse();


        GetCountryResponse response = new GetCountryResponse();




        if(retrievedCountry!=null)
        {
            response.setCountry(retrievedCountry); //set service response in case of valid country alpha2code
        }
        else
        {
            /*retrievedCountry = new Country();
            retrievedCountry.setMessage("Possible Country alpha2code values are : {in, us or ch}. Please use according country alpha2code!");
            response.setCountry(retrievedCountry);*/




            /* ENABLE This when using for soap-learncamel-springboot project where this is being used - So faultstring will contain full response
                    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                    <SOAP-ENV:Header/>
                    <SOAP-ENV:Body>
                        <SOAP-ENV:Fault>
                            <faultcode>SOAP-ENV:Server</faultcode>
                            <faultstring xml:lang="en">ERROR - NOT FOUND - Possible Country alpha2code values are : {in, us or ch}. Please use according country alpha2code!</faultstring>
                            <detail>
                                <code>NOT_FOUND</code>
                                <description>Possible Country alpha2code values are : {in, us or ch}. Please use according country alpha2code!</description>
                            </detail>
                        </SOAP-ENV:Fault>
                    </SOAP-ENV:Body>
                </SOAP-ENV:Envelope>


            throw new ServiceFaultException("ERROR - NOT FOUND - Possible Country alpha2code values are : {in, us or ch}. Please use according country alpha2code!",
                    new ServiceFault("NOT_FOUND",
                                    "Possible Country alpha2code values are : {in, us or ch}. Please use according country alpha2code!" ));*/


            throw new ServiceFaultException("ERROR",
                    new ServiceFault("NOT_FOUND", "# "+request.getCountryCode() + " # being passed as country code in request. Possible Country code values are : {in, us or ch}. Use according country alpha2code!" ));


        }


        return response; //return service response

    }

}
