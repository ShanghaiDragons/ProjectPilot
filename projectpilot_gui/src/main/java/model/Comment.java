package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import model.User;

/**
 * This comment class represents a comment in  a task, a column, or another comment. 
 */
public class Comment {
    private UUID id;
    private User user;
    private LocalDateTime date;
    private String message;
    private ArrayList<Comment> thread;

    /**
     * Constructor for new comment
     * @author ctaks
     * @param user new user
     * @param message new message
     */
    public Comment(User user, String message) {
        setID(this.id);
        setUser(user);
        setDate(this.date);
        setMessage(message);
        setThread(this.thread);
    }

    /**
     * Constructor for loading a comment from JSON
     * @author ctaks
     * @param id from JSON file
     * @param user from JSON file
     * @param date from JSON file
     * @param message from JSON file
     * @param thread from JSON file
     */
    public Comment(UUID id, User user, LocalDateTime date, String message, ArrayList<Comment> thread) {
        setID(id);
        setUser(user);
        setDate(this.date);
        setMessage(message);
        setThread(thread);
    }

    /**
     * Setter for id
     * @author ctaks
     * @param id to be set
     * @return boolean determining success
     */
    public boolean setID(UUID id) {
        if (id != null) {
            this.id = id;
            return true;
        }
        else {
            this.id = UUID.randomUUID();
            return true;
        }
    }

    /**
     * setter for user
     * @author ctaks
     * @param user to be set
     * @return boolean determining success
     */
    public boolean setUser(User user) {
        if (user != null) {
            this.user = user;
            return true;
        } else {
            this.user = null;
            return false;
        }
    }

    /**
     * setter for date
     * @author ctaks
     * @param date to be set
     * @return boolean determining success
     */
    public boolean setDate(LocalDateTime date) {
        if (date != null) {
            this.date = date;
            return true;
        } else {
            this.date = LocalDateTime.now();
            return false;
        }
    }

    /**
     * setter for message
     * @author ctaks
     * @param message to be set
     * @return boolean determining success
     */
    public boolean setMessage(String message) {
        if (message != null) {
            this.message = message;
            return true;
        } else {
            this.message = "empty";
            return false;
        }
    }

    public boolean setThread(ArrayList<Comment> thread) {
        if (thread == null || thread.isEmpty()) {
            this.thread = new ArrayList<Comment>();
            return false;
        } else {
            this.thread = thread;
            return true;
        }
    }

    /**
     * adding a comment on another comment, hence threading the comments
     * @author theo (edited by ctaks)
     * @param comment the comment to be added
     * @return boolean determining if the addition was a success
     */
    public boolean threadComment(User user, String message) {
        if(user != null && !message.isEmpty())
            return thread.add(new Comment(user, message));
        else
            return false;
    }

    /**
     * Gets the comment's assignee
     * @author ctaks
     * @return User of the user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Get's the comment's date
     * @author ctaks
     * @return LocalDateTime of the comment's date
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Get's a string of the comment's date formatted nicely
     * @author ctaks
     * @return String of the comment's date formatted nicely
     */
    public String getDateClean() {
        int hour = date.getHour();
        String ampm = "am";
        if (hour > 12) {
            hour -= 12;
            ampm = "pm";
        }
        String cleanDate = date.getMonthValue()+"-"+date.getDayOfMonth()+"-"+(date.getYear()-2000)+" @"+hour+":"+date.getMinute()+ampm;
        return cleanDate;
    }

    /**
     * Get's the comment's message
     * @author ctaks
     * @return String of the comment's message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Get's the comment's UUID
     * @author ctaks
     * @return UUID of the comment's UUID
     */
    public UUID getID() {
        return this.id;
    }

    /**
     * Get's the comment's thread
     * @author ctaks
     * @return ArrayList<Comment> of the comment's thread
     */
    public ArrayList<Comment> getThread() {
        return this.thread;
    }
}
