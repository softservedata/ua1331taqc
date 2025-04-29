package com.softserve.edu07fwk.api;

import java.util.Objects;

public class SigninResponse {
    private String userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private String ownRegistrations;

    public SigninResponse(String userId, String accessToken, String refreshToken, String name, String ownRegistrations) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.name = name;
        this.ownRegistrations = ownRegistrations;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getName() {
        return name;
    }

    public String getOwnRegistrations() {
        return ownRegistrations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SigninResponse that = (SigninResponse) o;
        return Objects.equals(userId, that.userId) && Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken) && Objects.equals(name, that.name) && Objects.equals(ownRegistrations, that.ownRegistrations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accessToken, refreshToken, name, ownRegistrations);
    }

    @Override
    public String toString() {
        return "SigninResponse{" +
                "userId='" + userId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", name='" + name + '\'' +
                ", ownRegistrations='" + ownRegistrations + '\'' +
                '}';
    }
}
