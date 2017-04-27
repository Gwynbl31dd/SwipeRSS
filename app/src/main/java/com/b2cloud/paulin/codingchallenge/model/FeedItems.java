package com.b2cloud.paulin.codingchallenge.model;

/**
 * Created by Anthony Paulin on 27-04-17.
 * Model object for the feed items.
 */

public class FeedItems {

    private int id;//Id in the JSON object
    private String title;//Title of the article
    private String date;//Formated String date
    private String image;//Picture
    private String link;//Link to the object

    /**
     * Construct an Items for the feed list
     * @param id the id (Position in the JSON object)
     * @param title title of the article
     * @param date date modified of the article
     * @param image url to an image
     * @param link link to the article
     */
    public FeedItems(int id, String title, String date, String image, String link) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.image = image;
        this.link = link;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Return a string formated representation of a date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return a link to a picture
     */
    public String getImage() {
        return image;
    }

    /**
     * @return a link to an article
     */
    public String getLink() {
        return link;
    }

    /**
     * @return return the id (position) of the article
     */
    public int getId() {
        return id;
    }
}
