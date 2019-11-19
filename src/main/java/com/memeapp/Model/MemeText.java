package com.memeapp.Model;

public class MemeText {

    private String upperText;
    private String bottomText;
    private boolean blackText;

    public MemeText(String upperText, String bottomText, boolean blackText) {
        this.upperText = upperText;
        this.bottomText = bottomText;
        this.blackText = blackText;
    }

    public String getUpperText() {
        return upperText;
    }

    public void setUpperText(String upperText) {
        this.upperText = upperText;
    }

    public String getBottomText() {
        return bottomText;
    }

    public void setBottomText(String bottomText) {
        this.bottomText = bottomText;
    }

    public boolean isBlackText() {
        return blackText;
    }

    public void setBlackText(boolean blackText) {
        this.blackText = blackText;
    }
}
