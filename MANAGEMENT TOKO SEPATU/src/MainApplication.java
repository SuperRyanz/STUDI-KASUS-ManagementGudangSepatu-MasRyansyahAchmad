import views.AdminLoginGUI;
import views.DashboardGUI;
import controllers.LoginSuccessListener;

public class MainApplication {
    public static void main(String[] args) {
        AdminLoginGUI loginGUI = new AdminLoginGUI();
        loginGUI.addLoginSuccessListener(new LoginSuccessListener() {
            @Override
            public void onLoginSuccess(String username) {
                // Buka DashboardGUI ketika login berhasil
                DashboardGUI dashboard = new DashboardGUI(username);
                dashboard.setVisible(true);
                // Jika Anda ingin, Anda dapat menutup jendela login di sini
                loginGUI.dispose();
            }
        });

        // Tampilkan jendela login
        loginGUI.setVisible(true);
    }
}
