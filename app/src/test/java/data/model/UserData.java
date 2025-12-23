package data.model;

public class UserData {
    private String nama;
    private String email;
    private String whatsapp;
    private String password;
    private String confirmPassword;

    public UserData(String nama, String email, String whatsapp,
                    String password, String confirmPassword) {
        this.nama = nama;
        this.email = email;
        this.whatsapp = whatsapp;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getNama() { return nama; }
    public String getEmail() { return email; }
    public String getWhatsapp() { return whatsapp; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
}
