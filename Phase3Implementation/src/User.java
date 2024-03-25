public abstract class User {
    protected String username;
    protected String password; // In a real application, passwords should be stored securely, not as plain text
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Placeholder for login functionality; specific logic to be implemented in subclasses
    public abstract boolean login(String username, String password);

    // Basic getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password; // Not recommended in real applications
    }

    public void setPassword(String password) {
        this.password = password; // Not recommended in real applications
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
