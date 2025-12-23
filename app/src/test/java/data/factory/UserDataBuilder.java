package data.factory;

import data.model.UserData;

public class UserDataBuilder {
    private String nama = "Test User";
    private String email = generateEmail();
    private String whatsapp = "089666111222";
    private String password = "Password123!";
    private String confirmPassword = password;

    public static UserDataBuilder builder() {
        return new UserDataBuilder();
    }

    public UserDataBuilder withNama(String nama) {
        this.nama = nama;
        return this;
    }

    public UserDataBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDataBuilder withWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
        return this;
    }

    public UserDataBuilder withPassword(String password) {
        this.password = password;
        this.confirmPassword = password;
        return this;
    }

    public UserDataBuilder withConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public UserData build() {
        return new UserData(
            nama,
            email,
            whatsapp,
            password,
            confirmPassword
        );
    }

    private static String generateEmail() {
        return "user_" + System.currentTimeMillis() + "@test.com";
    }
}
