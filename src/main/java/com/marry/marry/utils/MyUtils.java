package com.marry.marry.utils;

public class MyUtils {

    private static int id = 1;
    private final static char[] digits = "0123456789ABCDEF".toCharArray();


    /**
     *
     * @param hex 16进制字符串，支持大小写
     * @return byte数组
     */
    public static byte[] hexStringToBytes(String hex) {
        byte[] result = new byte[hex.length() / 2];
        char[] chars = hex.toCharArray();
        for (int i = 0, j = 0; i < result.length; i++) {
            result[i] = (byte) (charToByte(chars[j++]) << 4 | charToByte(chars[j++]));
        }
        return result;
    }

    private static int charToByte(char c) {
        if (c >= '0' && c <= '9') return (c - '0');
        if (c >= 'A' && c <= 'F') return (c - 'A' + 0x0A);
        if (c >= 'a' && c <= 'f') return (c - 'a' + 0x0a);
        throw new RuntimeException("invalid hex char '" + c + "'");
    }



    /**
     * @param bytes byte数组
     * @return 16进制字符串
     */
    public static String bytesToHexString(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int c = 0;
        for (byte b : bytes) {
            buf[c++] = digits[(b >> 4) & 0x0F];
            buf[c++] = digits[b & 0x0F];
        }
        return new String(buf);
    }

    /**
     * @return byte数组
     */
    public static byte[] intToByteArray() {
        byte[] result = new byte[32];
        result[28] = (byte)((id >> 24) & 0xFF);
        result[29] = (byte)((id>> 16) & 0xFF);
        result[30] = (byte)((id >> 8) & 0xFF);
        result[31] = (byte)(id & 0xFF);

        id ++;
        System.out.println(id);
        return result;
    }
}
