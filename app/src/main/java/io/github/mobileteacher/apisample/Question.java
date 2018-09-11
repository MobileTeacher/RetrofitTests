package io.github.mobileteacher.apisample;

import com.google.gson.annotations.SerializedName;

public class Question {

    public String title;
    public String link;

    @SerializedName("question_id")
    public int questionId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return(title);
    }
}
