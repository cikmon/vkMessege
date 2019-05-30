package com.example.vkMessege.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Data
@Document
public class Coub {

    @Id
    private String id;
    private String permalink;
    private String created_at;
    private List<String> tagsAndCategories;


    public void addTagsAndCategories(String categories){
        this.tagsAndCategories.add(categories);
    }
    public void addTagsAndCategories(List categories){
        this.tagsAndCategories.addAll(categories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coub)) return false;
        Coub coub = (Coub) o;
        return Objects.equals(getPermalink(), coub.getPermalink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPermalink());
    }

    public void setPermalink(String permalink){
        this.permalink=permalink;
        id=permalink;
    }
}
