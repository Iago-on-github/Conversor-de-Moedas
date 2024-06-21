package HTTPSolicitations;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Solicitations {
    private String apiKey = "88e2a880151d61d9f01e78d8";
    private String endress;
    private String baseCurrency; // Moeda padrão
    private String targetCurrency; // Moeda que será convertida

    public void setCurrencies(String baseCurrency, String targetCurrency) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.endress = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
    }

    public double requestRate() throws IOException, InterruptedException {
        if (endress == null || endress.isEmpty()) {
            throw new IllegalStateException("Sorry, the endress is not configured");
        }

        // Requisição client
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endress))
                .build();

        // Resposta client
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Processando a resposta JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        if (jsonObject.get("result").getAsString().equals("success")) {
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
            return conversionRates.get(targetCurrency).getAsDouble();
        } else {
            throw new IllegalStateException("Error getting exchenge rates.");
        }
    }
}
