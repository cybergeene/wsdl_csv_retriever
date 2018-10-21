package org.mednov.wsdl_csv.endpoint;


import org.mednov.wsdl_csv.controller.FindByNumber;
import org.mednov.wsdl_csv.controller.FindByNumberController;
import org.mednov.wsdl_csv.web_service.GetFindByNumberRequest;
import org.mednov.wsdl_csv.web_service.GetFindByNumberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class FindByNumberEndpoint {
    private static final String NAMESPACE_URI = "http://mednov.org/wsdl_csv/web-service";

    @Autowired
    private FindByNumber findByNumber;

//    private FindByNumberController findByNumberController;

/*    @Autowired
    public FindByNumberEndpoint(FindByNumberController findByNumberController) {
        this.findByNumberController = findByNumberController;
    }*/

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFindByNumberRequest")
    @ResponsePayload
    public GetFindByNumberResponse getCountryByName(@RequestPayload GetFindByNumberRequest request) {
        GetFindByNumberResponse response = new GetFindByNumberResponse();
        response.setResult(findByNumber.findByNumber(request.getName()));

        return response;
    }
}
