
# Country SOAP-WS using Spring Boot!!
--------------------------------------

#### (1) Created contract First/WSDL first Web Service (Top-Down Approach)
#### (2) Service will accept countries alpha2code which are in memory, like {us for USA, in for india, and ch for SWITZERLAND}. Service returen country capital name, population etc..
#### (3) Created domain classes using jaxb plugins
#### (4) Service handles customized soap fault message with details
#### (5) Service serve as boiler plate code, it can be extended to use database, and also can be extended to have more CRUD operations.







## More details:
--------------------------------------
##### [Service WSDL](http://localhost:8091/soap-ws/country.wsdl)
##### [Service URL](http://localhost:8091/soap-ws)
##### [Service XSD Schema](https://github.com/DRModi/springboot-soap-country-api/blob/master/src/main/resources/country.xsd)
##### [Sample Request and Response](https://github.com/DRModi/springboot-soap-country-api/blob/master/src/main/resources/soap-exception-usa.xml)
##### [Sample Exception Request and Response](https://github.com/DRModi/springboot-soap-country-api/blob/master/src/main/resources/soap-exception-usa.xml)

##### [Additional Information](https://github.com/DRModi/springboot-soap-country-api/blob/master/additionalInformation)
