package a.droid.libx.core.encrypt;

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
     * @param isNewline if a newline, a newline separator CTRF  is added every 76 bytes
     * @return encoded result
     */
    public byte[] encode(String data, boolean isNewline) {
        return encode(data.getBytes(), isNewline);
    }

    /**
     * @param data      the data to encode
     * @param isNewline if a newline, a newline separator CTRF  is added every 76 bytes
     * @return encoded result
     */
    public byte[] encode(byte[] data, boolean isNewline) {
        if (!isNewline)
            return a.java.util.Base64.getEncoder().encode(data);
        else
            return a.java.util.Base64.getMimeEncoder().encode(data);
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
        if (lineLength < 1 && lineSeparator.trim().isEmpty()) {
            throw new IllegalArgumentException("lineLength must be greater than 1 and lineSeparator length must be not empty");
        }
        return a.java.util.Base64.getMimeEncoder(lineLength, lineSeparator.getBytes()).encode(data);
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
     * @param hasLineSeparator the encoded data has or has not line separator
     * @return decoded result
     */
    public byte[] decode(String data, boolean hasLineSeparator) {
        return decode(data.getBytes(), hasLineSeparator);
    }

    /**
     * @param data             the data to decode
     * @param hasLineSeparator the encoded data has or has not line separator
     * @return decoded result
     */
    public byte[] decode(byte[] data, boolean hasLineSeparator) {
        if (!hasLineSeparator)
            return a.java.util.Base64.getDecoder().decode(data);
        else
            return a.java.util.Base64.getMimeDecoder().decode(data);
    }
}
