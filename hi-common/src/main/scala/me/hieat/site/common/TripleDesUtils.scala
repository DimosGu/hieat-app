package me.hieat.site.common

import java.security.{Key, MessageDigest, Security}
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.util.encoders.Hex

/**
 * TripleDesUtils
 * Created by Yang Jing (yangbajing@gmail.com) on 2015-10-01.
 */
class TripleDesUtils(key: String) {
  private val TRIPLE_DES_TRANSFORMATION = "DESede/ECB/PKCS7Padding"
  private val ALGORITHM = "DESede"
  private val BOUNCY_CASTLE_PROVIDER = "BC"
  private val PASSWORD_HASH_ALGORITHM = "SHA"
  private val UNICODE_FORMAT = "UTF-8"

  Security.addProvider(new BouncyCastleProvider())

  private def encode(input: Array[Byte], key: String): Array[Byte] = {
    val encrypter = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION, BOUNCY_CASTLE_PROVIDER)
    encrypter.init(Cipher.ENCRYPT_MODE, buildKey(key.toCharArray))
    encrypter.doFinal(input)
  }

  private def decode(input: Array[Byte], key: String): Array[Byte] = {
    val decrypter = Cipher.getInstance(TRIPLE_DES_TRANSFORMATION, BOUNCY_CASTLE_PROVIDER)
    decrypter.init(Cipher.DECRYPT_MODE, buildKey(key.toCharArray))
    decrypter.doFinal(input)
  }

  private def buildKey(password: Array[Char]): Key = {
    val digester = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM)
    digester.update(String.valueOf(password).getBytes(UNICODE_FORMAT))
    val keys: Array[Byte] = digester.digest
    val keyDes: Array[Byte] = java.util.Arrays.copyOf(keys, 24)
    new SecretKeySpec(keyDes, ALGORITHM)
  }

  def encrypt(plainText: String): String = {
    encrypt(plainText, key)
  }

  def encrypt(plainText: String, key: String): String = {
    val encryptedByte: Array[Byte] = encode(plainText.getBytes(UNICODE_FORMAT), key)
    //    Hex.encodeHexString(encryptedByte)
    Hex.toHexString(encryptedByte)
  }

  def decrypt(cipherText: String, key: String): String = {
    //    val decryptedByte: Array[Byte] = decode(Hex.decodeHex(cipherText.toCharArray), key)
    val decryptedByte: Array[Byte] = decode(Hex.decode(cipherText), key)
    new String(decryptedByte)
  }

  def decrypt(cipherText: String): String = {
    decrypt(cipherText, key)
  }

}
