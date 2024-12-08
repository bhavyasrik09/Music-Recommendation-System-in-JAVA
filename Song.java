import javax.swing.ImageIcon;

public class Song {
    private String trackName;
    private String artistName;
    private String genre;
    private int bpm;
    private double energy;
    private double danceability;
    private int popularity;
    private String imagePath; // Path to the song image

    public Song(String trackName, String artistName, String genre, int bpm, double energy, double danceability, int popularity, String imagePath) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.genre = genre;
        this.bpm = bpm;
        this.energy = energy;
        this.danceability = danceability;
        this.popularity = popularity;
        this.imagePath = imagePath;
    }

    // Getters for the properties
    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getGenre() {
        return genre;
    }

    public int getBpm() {
        return bpm;
    }

    public double getEnergy() {
        return energy;
    }

    public double getDanceability() {
        return danceability;
    }

    public int getPopularity() {
        return popularity;
    }

    public ImageIcon getImage() {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        if (imageIcon.getIconWidth() == -1) {
            System.out.println("Image not found at path: " + imagePath);
        } else {
            System.out.println("Image loaded successfully: " + imagePath);
        }
        return imageIcon;
    }
}