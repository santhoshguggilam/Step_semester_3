import java.util.Arrays;

public class Playlist {

    private final String[] songs;
    private int count;

    public Playlist(int maxSize) {
        this.songs = new String[maxSize];
        this.count = 0;
    }

    public void addSong(String song) {
        if (song != null && count < songs.length) {
            songs[count++] = song;
        }
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, count);
    }

    public int getSongCount() {
        return count;
    }

    public static void main(String[] args) {
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");
        String[] copy = p.getSongs();
        copy[0] = "Hacked";
        System.out.println("copy[0] = \"Hacked\"; // p.getSongs()[0] is still \"" + p.getSongs()[0] + "\"");
    }
}
