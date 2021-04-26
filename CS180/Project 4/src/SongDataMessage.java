import java.io.Serializable;

/**
 * @author Jack Bauer, Sithu Aung
 *
 * @version 4/10/2019
 *
 * A message encapsulating the byte data of a song. Multiple of these messages should be sent for each song
 * because songs should be broken up into segments of 1000 bytes or less.
 */
public class SongDataMessage implements Serializable {
    private byte[] data;

    public SongDataMessage(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
