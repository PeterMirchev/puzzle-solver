package com.puzzlesolver.model;

public class ImageResponse {

    private Mat image;

    public ImageResponse(Mat image) {
        this.image = image;
    }

    public Mat getImage() {
        return image;
    }

    public void setImage(Mat image) {
        this.image = image;
    }
}
