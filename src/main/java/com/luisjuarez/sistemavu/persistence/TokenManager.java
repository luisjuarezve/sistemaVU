package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class TokenManager {

    private static final String EMAIL_FROM = "sistemavu1@gmail.com";
    private static final String EMAIL_PASSWORD = "lmzculazogabbsqb";

    // Método para generar un token único
    public static String generateToken() {
        return java.util.UUID.randomUUID().toString();
    }

    // Método para enviar un token por correo electrónico
    public static void sendEmail(String to, String subject, String messageBody) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar un token en la base de datos
    public static void saveToken(String email, String token) {
        try (Connection conn = ConexionBDDMysql.getConnection()) {
            String query = "INSERT INTO tokens (email, token, expiration_time) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, token);

            LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);
            stmt.setString(3, expirationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al guardar el token: " + e.getMessage());
            e.printStackTrace();
        }

    }

    // Método para validar un token
    public static boolean validateToken(String email, String token) {
        boolean isValid = false;
        try (Connection conn = ConexionBDDMysql.getConnection()) {
            String query = "SELECT * FROM tokens WHERE email = ? AND token = ? AND expiration_time > ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, token);
            stmt.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    // Método para eliminar un token
    public static void deleteToken(String token) {
        try (Connection conn = ConexionBDDMysql.getConnection()) {
            String query = "DELETE FROM tokens WHERE token = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, token);

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
