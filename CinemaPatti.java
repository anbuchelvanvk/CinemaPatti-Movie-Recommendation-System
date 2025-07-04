import java.util.*;

public class CinemaPatti {

    static class Movie {
        private String title, genre, synopsis;
        private int releaseYear;
        private List<String> actors = new ArrayList<>();
        private List<String> platforms = new ArrayList<>();
        private List<Integer> ratings = new ArrayList<>();
        private List<String> reviews = new ArrayList<>();

        public Movie(String title, String genre, int year, String synopsis, List<String> actors,
                List<String> platforms) {
            this.title = title;
            this.genre = genre;
            this.releaseYear = year;
            this.synopsis = synopsis;
            this.actors = actors;
            this.platforms = platforms;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
        }

        public List<String> getReviews() {
            return reviews;
        }

        public void addRating(int rating) {
            ratings.add(rating);
        }

        public void addReview(String review) {
            reviews.add(review);
        }

        public double getAverageRating() {
            return ratings.isEmpty() ? 0.0 : ratings.stream().mapToInt(r -> r).average().orElse(0.0);
        }

        public String getDetails() {
            return "\n" + title + " (" + releaseYear + ") [" + genre + "]" +
                    "\nRating: " + String.format("%.2f", getAverageRating()) +
                    "\nSynopsis: " + synopsis +
                    "\nCast: " + String.join(", ", actors) +
                    "\nAvailable on: " + String.join(", ", platforms) +
                    "\nReviews:\n" + (reviews.isEmpty() ? "No reviews yet." : String.join("\n", reviews));
        }

        public String toString() {
            return title + " (" + releaseYear + ") [" + genre + "] | " +
                    String.format("%.2f", getAverageRating());
        }
    }

    static class User {
        private String name;
        private Set<String> watchedGenres = new HashSet<>();

        public User(String name) {
            this.name = name;
        }

        public void addWatchedGenre(String genre) {
            watchedGenres.add(genre);
        }

        public Set<String> getWatchedGenres() {
            return watchedGenres;
        }

        public String getName() {
            return name;
        }
    }

    static class Review {
        private User user;
        private Movie movie;
        private String reviewText;
        private int rating;

        public Review(User user, Movie movie, String text, int rating) {
            this.user = user;
            this.movie = movie;
            this.reviewText = text;
            this.rating = rating;
        }

        public void apply() {
            movie.addRating(rating);
            movie.addReview(user.getName() + " : " + reviewText);
            user.addWatchedGenre(movie.getGenre());
        }
    }

    static class MovieManager {
        private Map<String, Movie> movieMap = new HashMap<>();

        public void addMovie(Movie movie) {
            movieMap.put(movie.getTitle().toLowerCase(), movie);
        }

        public Movie getMovie(String title) {
            return movieMap.get(title.toLowerCase());
        }

        public Collection<Movie> getAllMovies() {
            return movieMap.values();
        }

        public List<Movie> getTopRated(int count) {
            List<Movie> sorted = new ArrayList<>(movieMap.values());
            sorted.sort((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating()));
            return sorted.subList(0, Math.min(count, sorted.size()));
        }
    }

    static class RecommendationEngine {
        private MovieManager movieManager;

        public RecommendationEngine(MovieManager manager) {
            this.movieManager = manager;
        }

        public List<Movie> recommend(User user, String genre) {
            List<Movie> pool = new ArrayList<>();
            for (Movie m : movieManager.getAllMovies()) {
                if (m.getGenre().toLowerCase().contains(genre.toLowerCase())) {
                    pool.add(m);
                }
            }
            pool.sort((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating()));
            return pool.subList(0, Math.min(5, pool.size()));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieManager manager = new MovieManager();
        RecommendationEngine recommender = new RecommendationEngine(manager);

        addIMDbTopTamilMovies(manager);

        System.out.print("Enter your name: ");
        String nam = sc.nextLine();
    User user = new User(nam);

    System.out.println("\nVanakkam " + nam + "! Nalla irukinga nu namburom! CinemaPatti ku ungalai anbudan varaverkirom!");
    System.out.print("\nApde 'Enter' button click panni ulla ponga.. neenga namma virundhaali!");
    String dum = sc.nextLine();

    while (true) {
        System.out.println("\nCinemaPatti Menu :" + dum);
        System.out.println("1. Namma 'Movies List' paarkaa, 1-ai azhuthavum..");
        System.out.println("2. 'Review submit' seiya, 2-ai azhuthavum..");
        System.out.println("3. 'Top Rated Movies' check panna, 3-ai azhuthavum..");
        System.out.println("4. 'Movie Recommendations' ku, 4-ai azhuthavum..");
        System.out.println("5. Engalai vittu 'Exit' aaga, 5-ai azhuthavum.. (Miss pannadhinga.. apram varuthapaduveenga)");
        System.out.print("Choose an option: ");


            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    List<Movie> allMovies = new ArrayList<>(manager.getAllMovies());
                    if (allMovies.isEmpty()) {
                        System.out.println("No movies available.");
                        break;
                    }
                    System.out.println("\nNaanga store panniruka movies :");
                    for (int i = 0; i < allMovies.size(); i++) {
                        System.out.println((i + 1) + ". " + allMovies.get(i).getTitle());
                    }
                    System.out.print("Endha movie oda details venumo, adhoda number anupunga : ");
                    int index = Integer.parseInt(sc.nextLine()) - 1;
                    if (index >= 0 && index < allMovies.size()) {
                        System.out.println(allMovies.get(index).getDetails());
                    } else {
                        System.out.println("Thavaraana padhivu..");
                    }
                    break;

                case 2:
                    System.out.print("Enter movie title to review: ");
                    String title = sc.nextLine();
                    Movie movie = manager.getMovie(title);

                    if (movie == null) {
                        System.out.println("Movie enga list la illa.. add pannuvom vaanga..!");
                        System.out.print("Enter genre: ");
                        String genre = sc.nextLine();
                        System.out.print("Enter release year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter synopsis: ");
                        String synopsis = sc.nextLine();
                        System.out.print("Enter actors (comma-separated): ");
                        List<String> actors = Arrays.asList(sc.nextLine().split(",\\s*"));
                        System.out.print("Enter platforms (comma-separated): ");
                        List<String> platforms = Arrays.asList(sc.nextLine().split(",\\s*"));

                        movie = new Movie(title, genre, year, synopsis, actors, platforms);
                        manager.addMovie(movie);
                        System.out.println("Movie add panniyaachu "+nam+" machii!\n");
                    }

                    System.out.print("Enter rating (1-10): ");
                    int rating = Integer.parseInt(sc.nextLine());
                    System.out.print("Write your review: ");
                    String reviewText = sc.nextLine();

                    Review r = new Review(user, movie, reviewText, rating);
                    r.apply();
                    System.out.println("Review submit panniyaachu " +nam+ " machii!");
                    break;

                case 3:
                    System.out.println("\nTop Rated Movies:");
                    manager.getTopRated(10).forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Ungaluku enna genre pudikkum nu sollunga (e.g., Action, Romance, Thriller): ");
                    String genre = sc.nextLine();
                    System.out.println("\nUngalukku suit-aana padangal:");
                    recommender.recommend(user, genre).forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Sendruvaarungal " + nam + ".. Meendum varuga!");
                    return;

                default:
                    System.out.println("Thavaraana padhivu..");
            }
        }
    }

    private static void addIMDbTopTamilMovies(MovieManager manager) {
        addMovieWithRating(manager, "Nayakan", "Crime Drama", 1987, 86,
                "A slum dweller rises to power in the Mumbai underworld.",
                List.of("Kamal Haasan", "Saranya", "Janagaraj"),
                List.of("Amazon Prime", "YouTube"));

        addMovieWithRating(manager, "Anbe Sivam", "Comedy Drama", 2003, 86,
                "Two strangers with opposing ideologies travel together and discover the meaning of love and compassion.",
                List.of("Kamal Haasan", "Madhavan"),
                List.of("Sun NXT", "YouTube"));

        addMovieWithRating(manager, "Pariyerum Perumal", "Social Drama", 2018, 86,
                "A law student from a marginalized community faces caste discrimination.",
                List.of("Kathir", "Anandhi"),
                List.of("Amazon Prime"));

        addMovieWithRating(manager, "96", "Romance", 2018, 85,
                "Two high school sweethearts meet at a reunion and relive their past.",
                List.of("Vijay Sethupathi", "Trisha"),
                List.of("Sun NXT", "Amazon Prime"));

        addMovieWithRating(manager, "Soorarai Pottru", "Biopic/Drama", 2020, 86,
                "Inspired by the life of Air Deccan founder G. R. Gopinath, a man dreams of launching a low-cost airline.",
                List.of("Suriya", "Aparna Balamurali"),
                List.of("Amazon Prime"));

        addMovieWithRating(manager, "Thevar Magan", "Family Drama", 1992, 87,
                "A London-educated man returns to his village and is forced to take up his father's legacy.",
                List.of("Kamal Haasan", "Sivaji Ganesan", "Revathi"),
                List.of("Amazon Prime", "YouTube"));

        addMovieWithRating(manager, "Visaaranai", "Crime Thriller", 2015, 84,
                "Four laborers are tortured by the police to confess to a crime they didn't commit.",
                List.of("Dinesh", "Samuthirakani", "Aadukalam Murugadoss"),
                List.of("Netflix"));

        addMovieWithRating(manager, "Thalapathi", "Action Drama", 1991, 85,
                "A man with a violent past becomes the trusted lieutenant of a local don.",
                List.of("Rajinikanth", "Mammootty", "Arvind Swami"),
                List.of("Amazon Prime", "YouTube"));

        addMovieWithRating(manager, "Kaithi", "Action Thriller", 2019, 84,
                "An ex-convict must deliver a truck full of poisoned cops to safety while evading gangsters.",
                List.of("Karthi", "Narain", "George Maryan"),
                List.of("Hotstar"));

        addMovieWithRating(manager, "Anniyan", "Psychological Thriller", 2005, 83,
                "A man with multiple personality disorder fights corruption in society.",
                List.of("Vikram", "Sadha", "Prakash Raj"),
                List.of("Netflix", "Sun NXT"));
    }

    private static void addMovieWithRating(MovieManager manager, String title, String genre, int year, int rating,
            String synopsis, List<String> actors, List<String> platforms) {
        Movie m = new Movie(title, genre, year, synopsis, actors, platforms);
        m.addRating(rating);
        manager.addMovie(m);
    }
}