package cn.hdbase.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/7.
 */

@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity<Integer> {

    private Date createdTime;

    private Date modifiedTime;

    @PrePersist
    public void beforeAdd(){
        modifiedTime=createdTime=new Date();
    }
    @PreUpdate
    public void beforeModified(){
        modifiedTime=new Date();
    }

    @Column(updatable = false, name = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreatedTime() {
        return createdTime;
    }
    @Column(updatable = false, name = "modified_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getModifiedTime() {
        return modifiedTime;
    }
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
