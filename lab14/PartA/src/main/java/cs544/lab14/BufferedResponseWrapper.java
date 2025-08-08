package cs544.lab14;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse original;
    private final byte[] body;

    public BufferedResponseWrapper(ClientHttpResponse response) throws IOException {
        this.original = response;
        this.body = response.getBody().readAllBytes(); // read and cache
    }

    @Override
    public InputStream getBody() {
        return new ByteArrayInputStream(body); // return new stream every time
    }

    // Delegate all other methods to the original response

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        return original.getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return original.getStatusText();
    }

    @Override
    public void close() {
        original.close();
    }

    @Override
    public org.springframework.http.HttpHeaders getHeaders() {
        return original.getHeaders();
    }
}
