import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // primeiro passo: fazer cenexão HTTP e buscar os top 250 da lista de filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        URI endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse <String> response = cliente.send(request, BodyHandlers.ofString());
        String body = response.body();
       //System.out.println(body);

       //segundo passo: extrair os dados que interessam da lista
       var parser = new JsonParser();
       List<Map<String, String>> listaDeFilmes = parser.parse(body);
       
       //System.out.println(listaDeFilmes.size());

       //exibir e manipular os dados
       for (Map<String,String> map : listaDeFilmes) {
        System.out.println(map.get("title"));
        System.out.println(map.get("image"));
        System.out.println(map.get("imDbRating"));
        
       }
      
    }
}
