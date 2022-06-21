package com.nocountry.travel.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSA {
    
    private PrivateKey privateKey;
    private PublicKey publicKey;

    private static final String PRIVATE_KEY_STRING = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKRsYxhlPA0ayVyBd1r8Rh3DKTHaN8dNLdD8C1/w04h09kBZn/RnlC9+3ayoiBl5k6LeFOo+/blsRnaclJyeCtgodXtlaeoqCci9jrBTiar9TZNgLZJlL2OgT5dpk8ulJERdGgPqe3plH8QPjmnuPIHK0V+3JFVQW/kGrKRdHKOlAgMBAAECgYAF86emPbfhEFvcnauFeOwZOxpivXyKZtiABmrRylf/fWuSiov+a8EF84IwgOZx6AaeyfCY4B0M5e0qpJVbXy8splxwhY3X3CgPw4MUWTK6zVjxnjKkK3XhzjxcMnVbgwx/WeF7ngFrHZaUEjsiwpxPsYG+RDK+/2VpbRCAtAawiwJBALZJwRtRLmtHj/hX05EflKDOSmwDwXQIsUSvfLXItQNbEa+9Z17OzhCpB/Vpyd1ym2rNL2GgbPbywMuQnuVos6cCQQDm6UyAE/vVjuWE3JOWLDto94ZBEOGU9Mp+9aSJWHeiLydDLu640jPmNeqUMOKRHC63qJ4QwJZJzk8yeS92wQfTAkBuRJdHywflZVi1xYr5krz5uVBTnsH7SvbQscciv9KfwQsl39EUDM2iTl2+Gsw95QIWzSAIUl8X0yjE+yrjYz6pAkAfC2Cgm48xEAwlh65D82BtZVHkb4uUhjCvTwE3AfDbmjFSI/4uVPxrR/ZoxAXB8qMJSB8wK2RJOfaF16sc8SjHAkADjNV4U7gTTMNuSkMZsU1cZm6B5KRzBfPuCKGDb1q9FqjskRiePcN0ImtEMXvlLSlo3F23bBLuCRW8468WqW76";
    private static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkbGMYZTwNGslcgXda/EYdwykx2jfHTS3Q/Atf8NOIdPZAWZ/0Z5Qvft2sqIgZeZOi3hTqPv25bEZ2nJScngrYKHV7ZWnqKgnIvY6wU4mq/U2TYC2SZS9joE+XaZPLpSREXRoD6nt6ZR/ED45p7jyBytFftyRVUFv5BqykXRyjpQIDAQAB";

    public void init(){
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();
        } catch (Exception ignored) {
        }
    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }
    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public String encrypt(String message) throws Exception{
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedMessage) throws Exception{
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage,"UTF8");
    }

    public void printKeys(){
        System.err.println("Public key\n"+ encode(publicKey.getEncoded()));
        System.err.println("Private key\n"+ encode(privateKey.getEncoded()));
    }

    public void initFromStrings(){
        try{
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY_STRING));
    
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    
            publicKey = keyFactory.generatePublic(keySpecPublic);
            privateKey = keyFactory.generatePrivate(keySpecPrivate);
        }catch (Exception ignored){}
    }

    public byte[] getPublicKey(){
        return publicKey.getEncoded();
    }
    public byte[] getPrivateKey(){
        return privateKey.getEncoded();
    }

    // public static void main(String[] args) {
    //     RSA rsa = new RSA();
    //     rsa.initFromStrings();
    //     try{
    //         String encryptedMessage = rsa.encrypt("Hello World");
    //         String decryptedMessage = rsa.decrypt(encryptedMessage);
    
    //         System.err.println("Encrypted:\n"+encryptedMessage);
    //         System.err.println("Decrypted:\n"+decryptedMessage);
    
    //     }catch (Exception ignored){}
    // }


}
