import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Song {
    String title;
    String artist;
    String genre;
    int bpm;
    double energy;
    double danceability;
    int popularity;
    String imagePath;

    public Song(String title, String artist, String genre, int bpm, double energy, double danceability, int popularity, String imagePath) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.bpm = bpm;
        this.energy = energy;
        this.danceability = danceability;
        this.popularity = popularity;
        this.imagePath = imagePath;
    }
}

// Custom JPanel to handle the background image scaling
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image scaled to fit the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class Music {
    private JFrame frame;
    private JPanel welcomePanel, songListPanel;
    private JTextField nameField, searchField;
    private JButton nextButton, searchButton, backButton, allButton;
    private List<Song> songs;

    public Music() {
        // Sample songs data with updated image paths
        songs = new ArrayList<>();
        songs.add(new Song("Shape of You", "Ed Sheeran", "Pop", 95, 0.8, 0.6, 90, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images1.jpg"));
        songs.add(new Song("Blinding Lights", "The Weeknd", "Pop", 171, 0.9, 0.7, 95, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images2.jpg"));
        songs.add(new Song("Señorita", "Shawn Mendes", "Pop", 117, 0.8, 0.65, 89, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images3.jpg"));
        songs.add(new Song("Someone You Loved", "Lewis Capaldi", "Pop", 110, 0.75, 0.6, 88, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images4.jpg"));
        songs.add(new Song("Bad Guy", "Billie Eilish", "Electropop", 135, 0.43, 0.7, 95, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images5.jpg"));
        songs.add(new Song("Happier", "Marshmello ft. Bastille", "Dance", 100, 0.73, 0.75, 90, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images6.jpg"));
        songs.add(new Song("Sunflower", "Post Malone and Swae Lee", "Hip-Hop", 90, 0.55, 0.7, 91, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images7.jpg"));
        songs.add(new Song("Break Free", "Ariana Grande", "Pop", 107, 0.78, 0.73, 89, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images8.jpg"));
        songs.add(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", "Funk", 115, 0.82, 0.71, 95, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images9.jpg"));
        songs.add(new Song("Believer", "Imagine Dragons", "Rock", 125, 0.87, 0.63, 91, "C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images10.jpg"));

        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Music Recommendation System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showWelcomePage();
    }

    private void showWelcomePage() {
        // Clear and reset for new user
        frame.getContentPane().removeAll();
        welcomePanel = new BackgroundPanel("C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images11.jpg"); // Set your welcome background image path
        welcomePanel.setLayout(null);

        JLabel nameLabel = new JLabel("Enter your name: ");
        nameLabel.setBounds(200, 250, 100, 30);
        nameField = new JTextField();
        nameField.setBounds(300, 250, 200, 30);

        nextButton = new JButton("Next");
        nextButton.setBounds(350, 300, 100, 30);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().trim().isEmpty()) {
                    showSongListPage();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter your name ");
                }
            }
        });

        welcomePanel.add(nameLabel);
        welcomePanel.add(nameField);
        welcomePanel.add(nextButton);
        frame.add(welcomePanel);
        frame.setVisible(true);
    }

    private void showSongListPage() {
        frame.getContentPane().removeAll();
        songListPanel = new BackgroundPanel("C:\\Users\\user\\Desktop\\all folders\\java programs\\New folder\\images12.jpg"); // Set your song list background image path

        songListPanel.setLayout(new BorderLayout());

        JLabel searchLabel = new JLabel("Enter genre:");
        searchField = new JTextField(10);
        searchButton = new JButton("Search");
        allButton = new JButton("All");

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showWelcomePage();
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(allButton);
        topPanel.add(backButton);

        JPanel songDisplayPanel = new JPanel();
        songDisplayPanel.setLayout(new BoxLayout(songDisplayPanel, BoxLayout.Y_AXIS));

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String genre = searchField.getText().trim();
                if (!genre.isEmpty()) {
                    displaySongs(songDisplayPanel, filterSongsByGenre(genre));
                }
            }
        });

        allButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displaySongs(songDisplayPanel, songs);
            }
        });

        // Initial display of all songs
        displaySongs(songDisplayPanel, songs);

        JScrollPane scrollPane = new JScrollPane(songDisplayPanel);
        songListPanel.add(topPanel, BorderLayout.NORTH);
        songListPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(songListPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void displaySongs(JPanel panel, List<Song> songs) {
        panel.removeAll();
        for (Song song : songs) {
            JPanel songPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            ImageIcon icon = new ImageIcon(new ImageIcon(song.imagePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            JLabel songImage = new JLabel(icon);
            JLabel songDetails = new JLabel("<html>Title: " + song.title + "<br/>Artist: " + song.artist + "<br/>Genre: " + song.genre + "</html>");
            
            songPanel.add(songImage);
            songPanel.add(songDetails);
            panel.add(songPanel);
        }
        panel.revalidate();
        panel.repaint();
    }

    private List<Song> filterSongsByGenre(String genre) {
        return songs.stream()
                    .filter(song -> song.genre.equalsIgnoreCase(genre))
                    .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Music());
    }
}
