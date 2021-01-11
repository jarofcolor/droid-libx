package a.droid.libx.core.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Base64 {

    /**
     * @param data the data to encode
     * @return encoded result
     */
    public byte[] encode(String data) {
        return encode(data, false);
    }

    /**
     * @param data the data to encode
     * @return encoded result
     */
    public byte[] encode(byte[] data) {
        return encode(data, false);
    }

    /**
     * @param data      the data to encode
     * @param isNewline if true, a newline separator CTRF  is added every 76 bytes
     * @return encoded result
     */
    public byte[] encode(String data, boolean isNewline) {
        return encode(data.getBytes(), isNewline);
    }

    /**
     * @param data      the data to encode
     * @param isNewline if true, a newline separator CTRF  is added every 76 bytes
     * @return encoded result
     */
    public byte[] encode(byte[] data, boolean isNewline) {
        return encode(data, 76, isNewline ? "\r\n" : "");
    }

    /**
     * @param data          the data to encode
     * @param lineLength    the length of each output line (rounded down to nearest multiple
     *                      of 4). If {@code lineLength <= 0} the output will not be separated
     *                      in lines
     * @param lineSeparator the line separator for each output line
     * @return encoded result
     */
    public byte[] encode(String data, int lineLength, String lineSeparator) {
        return encode(data.getBytes(), lineLength, lineSeparator);
    }

    /**
     * @param data          the data to encode
     * @param lineLength    the length of each output line (rounded down to nearest multiple
     *                      of 4). If {@code lineLength <= 0} the output will not be separated
     *                      in lines
     * @param lineSeparator the line separator for each output line
     * @return encoded result
     */
    public byte[] encode(byte[] data, int lineLength, String lineSeparator) {
        if (lineLength < 1) {
            throw new IllegalArgumentException("lineLength must be greater than 1");
        }
        return a.java.util.Base64.getMimeEncoder(lineLength, lineSeparator.getBytes()).encode(data);
    }

    /**
     * @param src the file to encode
     * @param dst encoded file
     * @return if true, the encoding succeeded
     */
    public boolean encode(File src, File dst) {
        return encode(src, dst, false);
    }

    /**
     * @param src       the file to encode
     * @param dst       encoded file
     * @param isNewline if true, a newline separator CTRF  is added every 76 bytes
     * @return if true, the encoding succeeded
     */
    public boolean encode(File src, File dst, boolean isNewline) {
        return encode(src, dst, 76, isNewline ? "\r\n" : "");
    }

    /**
     * @param src           the file to encode
     * @param dst           encoded file
     * @param lineLength    the length of each output line (rounded down to nearest multiple
     *                      of 4). If {@code lineLength <= 0} the output will not be separated
     *                      in lines
     * @param lineSeparator the line separator for each output line
     * @return if true, the encoding succeeded
     */
    public boolean encode(File src, File dst, int lineLength, String lineSeparator) {
        if (lineLength < 1) {
            throw new IllegalArgumentException("lineLength must be greater than 1");
        }
        try {
            a.java.util.Base64.Encoder encoder = a.java.util.Base64.getMimeEncoder(lineLength, lineSeparator.getBytes());
            OutputStream os = encoder.wrap(new FileOutputStream(dst));
            FileInputStream is = new FileInputStream(src);
            byte[] bytes = new byte[4 * 1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.close();
            is.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @param data the data to decode
     * @return decoded result
     */
    public byte[] decode(String data) {
        return decode(data, false);
    }

    /**
     * @param data the data to decode
     * @return decoded result
     */
    public byte[] decode(byte[] data) {
        return decode(data, false);
    }

    /**
     * @param data             the data to decode
     * @param hasLineSeparator if true, when decoding it will judge whether there is a line separator
     * @return decoded result
     */
    public byte[] decode(String data, boolean hasLineSeparator) {
        return decode(data.getBytes(), hasLineSeparator);
    }

    /**
     * @param data             the data to decode
     * @param hasLineSeparator if true, when decoding it will judge whether there is a line separator
     * @return decoded result
     */
    public byte[] decode(byte[] data, boolean hasLineSeparator) {
        if (!hasLineSeparator)
            return a.java.util.Base64.getDecoder().decode(data);
        else
            return a.java.util.Base64.getMimeDecoder().decode(data);
    }

    /**
     * @param src the src to decode
     * @param dst the dst is decoded file
     * @return decoded result
     */
    public boolean decode(File src, File dst) {
        return decode(src, dst, false);
    }

    /**
     * @param src              the src to decode
     * @param dst              the dst is decoded file
     * @param hasLineSeparator if true, when decoding it will judge whether there is a line separator
     * @return decoded result
     */

    public boolean decode(File src, File dst, boolean hasLineSeparator) {
        try {
            a.java.util.Base64.Decoder decoder = hasLineSeparator ? a.java.util.Base64.getMimeDecoder() : a.java.util.Base64.getDecoder();
            InputStream is = decoder.wrap(new FileInputStream(src));
            FileOutputStream os = new FileOutputStream(dst);
            byte[] bytes = new byte[4 * 1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.close();
            is.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
