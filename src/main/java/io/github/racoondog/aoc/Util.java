package io.github.racoondog.aoc;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    private static final String BASE_URL = "https://adventofcode.com/2023/day/%d/input";

    public static void downloadInput(int day, Path file) {
        if (!Files.exists(file.getParent())) {
            try {
                Files.createDirectories(file.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String sessionId = System.getenv("aoc-session");
        if (sessionId == null) throw new RuntimeException("You need to set the 'aoc-session' environment variable.");

        HttpCookie sessionCookie = new HttpCookie("session", sessionId);
        sessionCookie.setPath("/");
        sessionCookie.setVersion(0);

        CookieManager cookieManager = new CookieManager();
        cookieManager.getCookieStore().add(URI.create("https://adventofcode.com"), sessionCookie);

        try (HttpClient client = HttpClient.newBuilder().cookieHandler(cookieManager).build()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL.formatted(day)))
                .build();

            HttpResponse<Path> res = client.send(request, HttpResponse.BodyHandlers.ofFile(file));

            if (res.statusCode() == 400) {
                Files.delete(file);
                throw new RuntimeException("Invalid advent of code session id.");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
