package com.example.groupproject;

import com.chad.library.adapter.base.entity.SectionEntity;

public class Project extends SectionEntity<Project.projectItem> {
    public Project(boolean isHeader, String header, int headImgId) {
        super(isHeader, header);
        this.headImgId = headImgId;
    }

    public Project(projectItem bean) {
        super(bean);
    }

    private int headImgId;

    public int getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(int headImgId) {
        this.headImgId = headImgId;
    }

    public static class projectItem {
        private String title;
        private Boolean isSelect;
        private String link;
        private int imgId;

        public int getImgId() {
            return imgId;
        }

        public void setImgId(int imgId) {
            this.imgId = imgId;
        }

        public projectItem(String title, Boolean isSelect, String link, int imgId) {
            this.title = title;
            this.isSelect = isSelect;
            this.link = link;
            this.imgId = imgId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getSelect() {
            return isSelect;
        }

        public void setSelect(Boolean select) {
            isSelect = select;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
