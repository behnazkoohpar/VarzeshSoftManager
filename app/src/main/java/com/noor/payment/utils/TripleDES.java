package com.noor.payment.utils;

import android.annotation.SuppressLint;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TripleDES {

    public String encrypt(String message,String keyy) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest(keyy
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8; ) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        @SuppressLint("GetInstance") final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        String encryptedString = new String(Base64.encodeBase64(cipherText));
        System.out.println(encryptedString);

        return encryptedString;
    }

    public String decrypt(String message) throws Exception {
        message = "UbP5d3fSluR1oWlgD/5IHLIiLE540x5DEfjBYYsaxSADvz7vbCmfef2JdvAkaMQaYsm8zCVN2lo=";
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("AAECAwQFBgcICQoLDA0ODw=="
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8; ) {
            keyBytes[k++] = keyBytes[j++];
        }

        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        @SuppressLint("GetInstance") final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, key);

//        final byte[] encData = new sun.misc.BASE64Decoder().decodeBuffer(message);
        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] plainText = decipher.doFinal(plainTextBytes);

        return new String(plainText, "UTF-8");
    }

}
