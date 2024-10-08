import com.google.gson.Gson;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner inputUsuario = new Scanner(System.in);

        boolean condicionSalida = true;
        while(condicionSalida){
        System.out.println("Ingrese la moneda de entrada: (ARS/ USD / VES / GBP / BRL)");
            String monedaIn = inputUsuario.nextLine();
        System.out.println("Ingrese la moneda de salida: (ARS/ USD / VES / GBP / BRL)");
        String monedaOut = inputUsuario.nextLine();
        System.out.println("Ingrese el monto a convertir");
        int cantidad = inputUsuario.nextInt();
            inputUsuario.nextLine();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/77d8033cfefb98d785a4551c/latest/"+monedaIn.toUpperCase()))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        Respuesta respuesta = gson.fromJson(response.body(), Respuesta.class);
        double tasa = respuesta.getConversion_rates().get(monedaOut.toUpperCase());
        double valor = cantidad * tasa;
        System.out.println("El valor es de: " + valor);
            System.out.println("Ingrese '0' para salir o '1' para seguir convirtiendo");
            String salida = inputUsuario.nextLine();
            if(salida.equals("0")){
                condicionSalida = false;
            }
        }
    }
}