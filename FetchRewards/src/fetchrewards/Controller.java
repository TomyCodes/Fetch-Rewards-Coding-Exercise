/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fetchrewards;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class Controller {
    
    @FXML
    private TextArea dataTextArea;
    
    @FXML
    private void displayData() throws IOException {
        dataTextArea.clear();
        
        // API to handle Async operation
        HttpClient client = HttpClient.newHttpClient();
        // Build HttpRequest
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://fetch-hiring.s3.amazonaws.com/hiring.json")).build();
                  
        // Use client to send request asynchronously and receive the response body as a string
        // Appends the parsed JSON data to the JavaFX text area
        dataTextArea.appendText(client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body) // Use body method on the response body
                .thenApply(ParseData::parse) // Use parse method from ParseData.java
                .join());
    }
}
