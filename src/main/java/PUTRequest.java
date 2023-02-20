import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PUTRequest {

    public static void main(String[] args) throws IOException {
        Map<String, Object> productDetails = new HashMap<>();
        productDetails.put("colour", "red");
        productDetails.put("price", 500);
        Product productUpdated = new Product("IPhone 12", productDetails);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(productUpdated);

        URL url = new URL("https://api.restful-api.dev/objects/ff8081818643fc87018645c11de20016");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        // Send request to an API
        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(requestBody);
        }

        System.out.println("Response code: " + conn.getResponseCode());

        // Read Response from an API
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
