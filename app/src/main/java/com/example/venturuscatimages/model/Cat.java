package com.example.venturuscatimages.model;

import java.util.List;

public class Cat {

    private List<Data> data;

    public Cat(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public static class Data {
        private List<Images> images;

        public Data(List<Images> images) {
            this.images = images;
        }

        public List<Images> getImages() {
            return images;
        }
    }

    public static class Images {
        private String link;

        public Images(String link) {
            this.link = link;
        }

        public String getLink() {
            return link;
        }
    }
}