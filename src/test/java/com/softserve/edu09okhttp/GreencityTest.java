package com.softserve.edu09okhttp;

import com.google.gson.Gson;
import okhttp3.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreencityTest {
    private Gson gson;
    private OkHttpClient client;
    //
    private RequestBody requestBody;
    private Request request;
    //
    private String resultJson;
    private String token;


    @BeforeAll
    public void setup() {
        gson = new Gson();
        client = new OkHttpClient();
    }

    @AfterAll
    public void tear() {

    }

    private static Stream<Arguments> userProvider() {
        System.out.println("\tArguments sumProvider done");
        return Stream.of(
                Arguments.of("tyv09754@zslsz.com", "Qwerty_1")
        );
    }

    @Order(1)
    @ParameterizedTest(name = "{index} => email={0}, password={1}")
    @MethodSource("userProvider")
    public void checkLogin(String email, String password) {
        GreencityLogin greencityLogin;
        GreencityAllEvents greencityAllEvents;
        boolean isSuccess = false;
        //
        // Login
        String jsonBody = new StringBuilder()
                .append("{")
                .append("\"email\":\"" + email + "\",")
                .append("\"password\":\"" + password + "\",")
                .append("\"secretKey\":\"UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D\"")
                .append("}").toString();
        //
        requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        request = new Request.Builder()
                .url("https://greencity-user.greencity.cx.ua/api/testers/sign-in")
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        //
        try (Response response = client.newCall(request).execute()) {
            resultJson = response.body().string();
            greencityLogin = gson.fromJson(resultJson, GreencityLogin.class);
            token = greencityLogin.getAccessToken();
            isSuccess = response.isSuccessful();
        } catch (IOException e) {
            System.out.println("IOException e = " + e);
            throw new RuntimeException(e);
        }
        //
        System.out.println("resultJson = " + resultJson);
        System.out.println("token = " + token);
        //
        Assertions.assertTrue(isSuccess);
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource({"0", "1"})
    public void checkEvents(String page) {
        GreencityAllEvents greencityAllEvents;
        boolean isSuccess = false;
        //
        // Get all Events
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://greencity.greencity.cx.ua/events").newBuilder();
        urlBuilder.addQueryParameter("page", page);
        urlBuilder.addQueryParameter("size", "5");
        String url = urlBuilder.build().toString();
        //
        request = new Request
                .Builder()
                .url(url)
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();
        //
        try (Response response = client.newCall(request).execute()) {
            resultJson = response.body().string();
            greencityAllEvents = gson.fromJson(resultJson, GreencityAllEvents.class);
            isSuccess = response.isSuccessful();
        } catch (IOException e) {
            System.out.println("IOException e = " + e);
            throw new RuntimeException(e);
        }
        //
        Assertions.assertTrue(isSuccess);
        System.out.println("resultJson: " + resultJson);
        System.out.println("greencityAllEvents: " + greencityAllEvents);
    }
}
