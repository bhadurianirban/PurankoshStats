/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.purankoshstats;

import java.util.Date;

/**
 *
 * @author dgrfi
 */
public class EntryDetails {
    private Integer postWordCount;
    private String postTitle;
    private Integer postid;
    private Date postPublishDate;
    private String postAuthor;

    public EntryDetails(Integer postWordCount, String postTitle, Integer postid, Date postPublishDate, String postAuthor) {
        this.postWordCount = postWordCount;
        this.postTitle = postTitle;
        this.postid = postid;
        this.postPublishDate = postPublishDate;
        this.postAuthor = postAuthor;
    }

    public EntryDetails() {
    }
    
    public Integer getPostWordCount() {
        return postWordCount;
    }

    public void setPostWordCount(Integer postWordCount) {
        this.postWordCount = postWordCount;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public Date getPostPublishDate() {
        return postPublishDate;
    }

    public void setPostPublishDate(Date postPublishDate) {
        this.postPublishDate = postPublishDate;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }
    
}
