import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {




        String apiKey = "apikey";
        String url = "https://generativelanguage.googleapis.com" + apiKey;



        Scanner scanner = new Scanner(System.in);
        System.out.println("დაწერეთ დავალება ჯემინაისთვის:  ");
        String input = scanner.nextLine();



        String jsonPayload = """
          {
              "contents": [{
                "parts": [{"text":"%s"}]
              }]
            }
            """.formatted(input);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println( response.body());


        //json გაპარსვა და პასუხის წამოღება
        System.out.println("---------------------------answer--------------------------------------");
        String json = response.body();
        String afterText = json.split("\"text\": \"")[1];//დაყოფა და ტექსტის შემდეგ მეორე ნაწილის აღება
        String answer = afterText.split("\"")[0];// დაყოფა ბრჭყალებად და პირველი ნაწილის ამოღება
        System.out.println(answer);

    }
}
