package com.bookshop.bean;

public class BookBean {
    private Integer bookId;
    private String bookName;
    private Integer typeId;
    private String typeName;
    private String bookPic;
    private String author;
    private String publish;
    private String publicDate;
    private String depict;
    private Double price;
    private Integer storages;
    private Integer statu;
    private Integer delFlag;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStorages() {
        return storages;
    }

    public void setStorages(Integer storages) {
        this.storages = storages;
    }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", bookPic='" + bookPic + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", publicDate='" + publicDate + '\'' +
                ", depict='" + depict + '\'' +
                ", price=" + price +
                ", storages=" + storages +
                ", statu=" + statu +
                ", delFlag=" + delFlag +
                '}';
    }
}
