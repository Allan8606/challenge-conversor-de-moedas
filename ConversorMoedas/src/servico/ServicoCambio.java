package servico;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicoCambio {
    private static final String CHAVE_API = "4959cc533b2a31d4bef7feca";

    public static double converter(double valor, String moedaOrigem, String moedaDestino) throws IOException, InterruptedException {
        String url = String.format(
                "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s",
                CHAVE_API, moedaOrigem, moedaDestino);

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        int codigoStatus = resposta.statusCode();
        if (codigoStatus != 200) {
            throw new IOException("Erro HTTP ao chamar API: código " + codigoStatus);
        }

        String corpo = resposta.body();

        JsonReader leitor = new JsonReader(new StringReader(corpo));
        leitor.setLenient(true);

        JsonElement elemento = JsonParser.parseReader(leitor);
        JsonObject objetoJson = elemento.getAsJsonObject();

        String resultado = objetoJson.get("result").getAsString();
        if (!"success".equalsIgnoreCase(resultado)) {
            String tipoErro = objetoJson.has("error-type") ? objetoJson.get("error-type").getAsString() : "desconhecido";
            throw new IOException("Falha na API: " + tipoErro);
        }

        double taxaCambio = objetoJson.get("conversion_rate").getAsDouble();
        return valor * taxaCambio;
    }
}
