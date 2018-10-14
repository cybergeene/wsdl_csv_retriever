package org.mednov.wsdl_csv.endpoint;


import org.mednov.wsdl_csv.repository.FindByNumberRepository;
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

    private FindByNumberRepository findByNumberRepository;

    @Autowired
    public FindByNumberEndpoint(FindByNumberRepository findByNumberRepository) {
        this.findByNumberRepository = findByNumberRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFindByNumberRequest")
    @ResponsePayload
    public GetFindByNumberResponse getCountryByName(@RequestPayload GetFindByNumberRequest request) {
        GetFindByNumberResponse response = new GetFindByNumberResponse();
        response.setResult(findByNumberRepository.findByNumber(request.getName()));

        return response;
    }
}
