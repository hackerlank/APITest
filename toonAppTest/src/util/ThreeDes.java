package util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class ThreeDes {
	private static final String Algorithm = "desede" + "/CBC/PKCS5Padding"; // 定义 加密算法,可用
														// DES,DESede,Blowfish

	final static byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
			0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
			0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
			(byte) 0xE2 }; // 24字节的密钥
	
	
	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			Key deskey = null; 
			DESedeKeySpec spec = new DESedeKeySpec(keybyte); 
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede"); 
			deskey = keyfactory.generateSecret(spec);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			byte[] iv = new byte[8];
			IvParameterSpec ips = new IvParameterSpec(iv); 
			c1.init(Cipher.ENCRYPT_MODE, deskey,ips);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			Key deskey = null; 
			DESedeKeySpec spec = new DESedeKeySpec(keybyte); 
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede"); 
			deskey = keyfactory.generateSecret(spec);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			byte[] iv = new byte[8];
			IvParameterSpec ips = new IvParameterSpec(iv); 
			c1.init(Cipher.DECRYPT_MODE, deskey,ips);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

//	// 转换成十六进制字符串
//	public static String byte2hex(byte[] b) {
//		String hs = "";
//		String stmp = "";
//		for (int n = 0; n < b.length; n++) {
//			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
//			if (stmp.length() == 1)
//				hs = hs + "0" + stmp;
//			else
//				hs = hs + stmp;
//			if (n < b.length - 1)
//				hs = hs + ":";
//		}
//		return hs.toUpperCase();
//	}
	
	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) { // 一个字节的数，
		// 转成16进制字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// 整数转成十六进制表示
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase(); // 转成大写
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	
	public static String encrypt(String KeyMing)
	{
		try{
			byte[] encoded = encryptMode(keyBytes, KeyMing.getBytes());
			String str1 = byte2hex(encoded);
			return str1;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	public static String decrypt(String KeyMi)
	{
		try{
			byte[] encoded = hex2byte(KeyMi.getBytes());
			byte[] srcBytes = decryptMode(keyBytes, encoded);
			return new String(srcBytes);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	

	public static void main(String[] args) {

		// 添加新安全算法,如果用JCE就要把它添加进去
//		Security.addProvider(new com.sun.crypto.provider.SunJCE());

		String szSrc = "18611372001";//"This is a 3DES test. 测试";
		System.out.println("加密前的字符串:" + szSrc);
		String s1 = encrypt(szSrc);
		System.out.println("加密后的字符串:"+s1);
		String szSrc1 = decrypt(s1);
		System.out.println("解密后的字符串:"+szSrc1);
		System.out.println("对比结果="+szSrc1.equals(szSrc));

//		byte[] encoded = encryptMode(keyBytes, szSrc.getBytes());
//		String str1 = byte2hex(encoded);
//		System.out.println("byte2hex:" + str1);
//		System.out.println("加密后的字符串:" + new String(encoded));
//		encoded = hex2byte(str1.getBytes());
//		byte[] srcBytes = decryptMode(keyBytes, encoded);
//		System.out.println("解密后的字符串:" + (new String(srcBytes)));
	}
}