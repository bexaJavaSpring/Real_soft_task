package com.example.real_soft_task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class RealSoftTaskApplication {

    @Value(value = "${spring.sql.init.mode}")
    public static String mode;


    public static void main(String[] args) {
        SpringApplication.run(RealSoftTaskApplication.class, args);

        if (mode==("always")){
            final String DB_URL = "jdbc:postgresql://localhost:5432/Real-soft";
            final String USER = "postgres";
            final String PASS = "1234";



            try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement()){
                String sql = "CREATE TABLE \"Contact\"(\n" +
                        "    \"id\" serial ,\n" +
                        "    \"name\" VARCHAR(255) NULL,\n" +
                        "    \"number\" VARCHAR NOT NULL,\n" +
                        "    \"status\" VARCHAR NOT NULL,\n" +
                        "    \"category_id\" INTEGER NULL,\n" +
                        "    \"active\" BOOLEAN NOT NULL\n" +
                        ");\n" +
                        "ALTER TABLE\n" +
                        "    \"Contact\" ADD PRIMARY KEY(\"id\");\n" +
                        "ALTER TABLE\n" +
                        "    \"Contact\" ADD CONSTRAINT \"contact_name_unique\" UNIQUE(\"name\");\n" +
                        "CREATE TABLE \"Category\"(\n" +
                        "    \"id\" serial,\n" +
                        "    \"name\" VARCHAR(255) NULL,\n" +
                        "    \"active\" BOOLEAN NOT NULL\n" +
                        ");\n" +
                        "CREATE INDEX \"category_name_index\" ON\n" +
                        "    \"Category\"(\"name\");\n" +
                        "ALTER TABLE\n" +
                        "    \"Category\" ADD PRIMARY KEY(\"id\");\n" +
                        "ALTER TABLE\n" +
                        "    \"Category\" ADD CONSTRAINT \"category_name_unique\" UNIQUE(\"name\");\n" +
                        "CREATE TABLE \"history\"(\n" +
                        "    \"id\" serial,\n" +
                        "    \"user_id\" INTEGER NOT NULL,\n" +
                        "    \"created_at\" DATE NOT NULL,\n" +
                        "    \"action\" VARCHAR(255) NOT NULL,\n" +
                        "    \"object\" VARCHAR(255) NOT NULL,\n" +
                        "    \"objectName\" VARCHAR(255) NOT NULL\n" +
                        ");\n" +
                        "ALTER TABLE\n" +
                        "    \"history\" ADD PRIMARY KEY(\"id\");\n" +
                        "CREATE TABLE \"users\"(\n" +
                        "    \"id\" serial,\n" +
                        "    \"username\" VARCHAR(255) NULL,\n" +
                        "    \"email\" VARCHAR(255) NULL,\n" +
                        "    \"password\" VARCHAR(255) NULL,\n" +
                        "    \"role\" VARCHAR(255) NOT NULL,\n" +
                        "    \"active\" BOOLEAN NOT NULL\n" +
                        ");\n" +
                        "ALTER TABLE\n" +
                        "    \"users\" ADD PRIMARY KEY(\"id\");\n" +
                        "ALTER TABLE\n" +
                        "    \"users\" ADD CONSTRAINT \"users_email_unique\" UNIQUE(\"email\");\n" +
                        "ALTER TABLE\n" +
                        "    \"Contact\" ADD CONSTRAINT \"contact_category_id_foreign\" FOREIGN KEY(\"category_id\") REFERENCES \"Category\"(\"id\");\n" +
                        "ALTER TABLE\n" +
                        "    \"history\" ADD CONSTRAINT \"history_user_id_foreign\" FOREIGN KEY(\"user_id\") REFERENCES \"users\"(\"id\");";

                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


