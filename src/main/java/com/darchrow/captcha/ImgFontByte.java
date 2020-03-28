package com.darchrow.captcha;


import java.awt.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * ttf字体文件
 */
public class ImgFontByte {

    private static final String TTF_FONT_HEX_FILE_NAME = "fonts/ttfFontHex";

    private static String ttfFontHex;

    static {
        BufferedReader reader = null;
        try {
            String filePath = ImgFontByte.class.getClassLoader().getResource(TTF_FONT_HEX_FILE_NAME).getPath();
            reader = new BufferedReader(new FileReader(filePath));
            ttfFontHex = reader.readLine();
        } catch (IOException e) {
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public Font getFont(int fontHeight) {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new ByteArrayInputStream(hex2byte(getFontByteStr())));
            return baseFont.deriveFont(Font.PLAIN, fontHeight);
        } catch (Exception e) {
            return new Font("Arial", Font.PLAIN, fontHeight);
        }
    }

    private byte[] hex2byte(String str) {
        if (str == null) {
            return null;
        }
        str = str.trim();
        int len = str.length();
        if (len == 0 || len % 2 == 1) {
            return null;
        }
        byte[] b = new byte[len / 2];
        try {
            for (int i = 0; i < str.length(); i += 2) {
                b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ttf字体文件的十六进制字符串
     *
     * @return
     */
    private String getFontByteStr() {
        return ttfFontHex;
    }
}