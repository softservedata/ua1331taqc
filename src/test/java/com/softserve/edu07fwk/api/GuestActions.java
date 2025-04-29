package com.softserve.edu07fwk.api;

import com.google.gson.Gson;
import com.softserve.edu07fwk.data.User;
import okhttp3.*;

import java.io.IOException;

public class GuestActions {
    private static final String POST_URL = "https://greencity-user.greencity.cx.ua/api/testers/sign-in";
    //
    private static OkHttpClient client;
    private static Gson gson;

    public GuestActions() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public SigninResponse signin(User user) {
        String jsonBody = new StringBuilder()
                .append("{")
                .append("\"email\":\"" + user.getEmail() + "\",")
                .append("\"password\":\"" + user.getPassword() + "\",")
                .append("\"secretKey\":\"UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D\"")
                .append("}").toString();
        RequestBody requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(POST_URL)
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        Response response = null;
        String resultJson = "";
        try {
            response = client.newCall(request).execute();
            resultJson = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SigninResponse signinResponse = gson.fromJson(resultJson, SigninResponse.class);
        System.out.println("signinResponse = " + signinResponse);
        return signinResponse;
    }

}
