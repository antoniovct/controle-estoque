package br.com.antonio_victor.controle_estoque.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptConfig {

    public static String SenhaHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
    public static boolean checkSenha(String senhaNormal, String senhaHash) {
        return BCrypt.checkpw(senhaNormal, senhaHash);
    }
}
