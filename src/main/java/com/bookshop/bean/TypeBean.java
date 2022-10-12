package com.bookshop.bean;

public class TypeBean {

    private Integer typeId;
    private String typeName;
    private String typeDepict;
    private Integer delFlag;

    public TypeBean() {
    }

    public TypeBean(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
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

    public String getTypeDepict() {
        return typeDepict;
    }

    public void setTypeDepict(String typeDepict) {
        this.typeDepict = typeDepict;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "TypeBean{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDepict='" + typeDepict + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
