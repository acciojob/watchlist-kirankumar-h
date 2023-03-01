package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;



@Repository
public class MovieRepository {

    private HashMap<String,Movie> movieMap;

    private  HashMap<String,Director> directorMap;
    private HashMap<String, List<String>> directorMovieMap;

    public MovieRepository() {
        this.movieMap = new HashMap<>();
        this.directorMap = new HashMap<>();
        this.directorMovieMap = new HashMap<>();
    }

    public  void saveMovie(Movie movie) {
        movieMap.put(movie.getName(),movie);

    }


    public void saveDirector(Director director) {
        directorMap.put(director.getName(),director);
    }

    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> currentMovies = new ArrayList<>();

            if(directorMovieMap.containsKey(director))
                        currentMovies = directorMovieMap.get(director);
            currentMovies.add(movie);

            directorMovieMap.put(director,currentMovies);
        }
    }
    public Movie findMovie(String movieName){
        return movieMap.get(movieName);
    }
    public Director findDirector(String directorName){
        return directorMap.get(directorName);
    }

    public List<String> findMoviesOfDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMap.containsKey(director)) moviesList = directorMovieMap.get(director);
        return moviesList;

    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director) {
        List<String> movies = new ArrayList<String>();
        if(directorMovieMap.containsKey(director)){
            movies = directorMovieMap.get(director);
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            directorMovieMap.remove(director);
        }

        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector() {
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: directorMovieMap.keySet()){
            for(String movie: directorMovieMap.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
    }
}
