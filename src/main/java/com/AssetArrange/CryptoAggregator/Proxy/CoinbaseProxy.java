package com.AssetArrange.CryptoAggregator.Proxy;

import com.AssetArrange.CryptoAggregator.Constants.Constants;
import com.AssetArrange.CryptoAggregator.Core.Signature;
import com.AssetArrange.CryptoAggregator.init.coinbasePro.CoinbaseProSettings;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * This class acts as a central point for providing user configuration and making GET/POST/PUT requests as well as
 * getting responses as Lists of objects rather than arrays.
 */
public class CoinbaseProxy implements ICoinbaseProxy {

    private static final Logger log = LoggerFactory.getLogger(CoinbaseProxy.class.getName());

    private final String publicKey;
    private final String passphrase;
    private final String baseUrl;
    private final Signature signature;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public CoinbaseProxy(final CoinbaseProSettings settings) {
        this.baseUrl = settings.getCoinbaseProBaseUrl();
        this.passphrase = settings.getCoinbaseProPassphrase();
        this.publicKey = settings.getCoinbaseProPublicKey();
        this.signature = new Signature(settings.getCoinbaseProSecretKey());
        this.objectMapper = new ObjectMapper();
        this.restTemplate = new RestTemplate();
    }

    public CoinbaseProxy(final String baseUrl,
                         final String publicKey,
                         final String passphrase,
                         final String secretKey) {
        this.baseUrl = baseUrl;
        this.publicKey = publicKey;
        this.passphrase = passphrase;
        this.signature = new Signature(secretKey);
        this.objectMapper = new ObjectMapper();
        this.restTemplate = new RestTemplate();
    }

    @Override
    public <T> T get(String resourcePath, ParameterizedTypeReference<T> responseType) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(getBaseUrl() + resourcePath,
                    HttpMethod.GET,
                    securityHeaders(resourcePath, Constants.GET, Constants.EMPTY),
                    responseType);
            return responseEntity.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("GET request Failed for '" + resourcePath + "': " + ex.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public <T> List<T> getAsList(String resourcePath, ParameterizedTypeReference<T[]> responseType) {
        T[] result = get(resourcePath, responseType);

        return result == null ? emptyList() : Arrays.asList(result);
    }

    @Override
    public <T> T pagedGet(String resourcePath,
                          ParameterizedTypeReference<T> responseType,
                          String beforeOrAfter,
                          Integer pageNumber,
                          Integer limit) {
        resourcePath += "?" + beforeOrAfter + "=" + pageNumber + "&limit=" + limit;
        return get(resourcePath, responseType);
    }

    @Override
    public <T> List<T> pagedGetAsList(String resourcePath,
                                      ParameterizedTypeReference<T[]> responseType,
                                      String beforeOrAfter,
                                      Integer pageNumber,
                                      Integer limit) {
        T[] result = pagedGet(resourcePath, responseType, beforeOrAfter, pageNumber, limit );
        return result == null ? emptyList() : Arrays.asList(result);
    }

    @Override
    public <T> T delete(String resourcePath, ParameterizedTypeReference<T> responseType) {
        try {
            ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + resourcePath,
                    HttpMethod.DELETE,
                    securityHeaders(resourcePath, Constants.DELETE, Constants.EMPTY),
                    responseType);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("DELETE request Failed for '" + resourcePath + "': " + ex.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public <T, R> T post(String resourcePath,  ParameterizedTypeReference<T> responseType, R jsonObj) {
        String jsonBody = toJson(jsonObj);

        try {
            ResponseEntity<T> response = restTemplate.exchange(getBaseUrl() + resourcePath,
                    HttpMethod.POST,
                    securityHeaders(resourcePath, Constants.POST, jsonBody),
                    responseType);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            log.error("POST request Failed for '" + resourcePath + "': " + ex.getResponseBodyAsString());
        }
        return null;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public HttpEntity<String> securityHeaders(String endpoint, String method, String jsonBody) {
        HttpHeaders headers = new HttpHeaders();

        String timestamp = Instant.now().getEpochSecond() + Constants.EMPTY;
        String resource = endpoint.replace(getBaseUrl(), Constants.EMPTY);

        headers.add(Constants.ACCEPT, Constants.APPLICATION_JSON);
        headers.add(Constants.CONTANT_TYPE, Constants.APPLICATION_JSON);
        headers.add(Constants.USER_AGENT, Constants.USER_AGENT_NAME);
        headers.add(Constants.CB_ACCESS_KEY, publicKey);
        headers.add(Constants.CB_ACCESS_SIGN, signature.generate(resource, method, jsonBody, timestamp));
        headers.add(Constants.CB_ACCESS_TIMESTAMP, timestamp);
        headers.add(Constants.CB_ACCESS_PASSPHRASE, passphrase);

        curlRequest(method, jsonBody, headers, resource);

        return new HttpEntity<>(jsonBody, headers);
    }

    /**
     * Purely here for logging an equivalent curl request for debugging (Signature expires in 1 minute)
     */
    private void curlRequest(String method, String jsonBody, HttpHeaders headers, String resource) {
        StringBuilder builder = new StringBuilder();
        builder.append("curl ");
        builder.append(headers.keySet().stream()
                .map(key -> "-H '" + key + ":" + headers.get(key).get(0) + "' "));
        if (jsonBody != null && !jsonBody.equals(""))
            builder.append("-d '").append(jsonBody).append("' ");
        builder.append("-X ").append(method).append(" ").append(getBaseUrl()).append(resource);

        log.debug(builder.toString());
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize", e);
            throw new RuntimeException("Unable to serialize");
        }
    }
}
