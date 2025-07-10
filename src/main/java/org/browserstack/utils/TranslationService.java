package org.browserstack.utils;

import okhttp3.*;
import com.google.gson.*;

import java.io.IOException;
import java.util.List;

public class TranslationService {

    private static final String API_URL = "https://rapid-translate-multi-traduction.p.rapidapi.com/t";

    private final OkHttpClient client;
    private final Gson gson;
    private final String apiKey;

    public TranslationService(String apiKey) {
        this.apiKey = apiKey;
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<String> translateToEnglish(List<String> spanishTexts) throws IOException {
        // Build JSON request body
        JsonObject payload = new JsonObject();
        payload.addProperty("from", "es");
        payload.addProperty("to", "en");
        payload.addProperty("e", "");

        JsonArray qArray = new JsonArray();
        for (String text : spanishTexts) {
            qArray.add(text);
        }
        payload.add("q", qArray);

        RequestBody body = RequestBody.create(
                gson.toJson(payload),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-rapidapi-host", "rapid-translate-multi-traduction.p.rapidapi.com")
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Translation failed: " + response);
            }

            String responseBody = response.body().string();

            // Parse response array
            JsonArray jsonArray = gson.fromJson(responseBody, JsonArray.class);
            return gson.fromJson(jsonArray, List.class);
        }
    }
}
