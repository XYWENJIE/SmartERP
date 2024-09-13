package com.benjamin.smarterp.domain.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 该类是基本实体，所有都继承该类，ID字符串
 */
@MappedSuperclass
public class BasicEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;

    @Column(name = "CREATE_BY")
    @Temporal(TemporalType.TIMESTAMP)
    private String createBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "SYNC_STATUS")
    private SyncStatus syncStatus;

    @Column(name = "SYNC_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime syncAt;

    @Version
    @Column(name = "VERSION")
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public SyncStatus getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(SyncStatus syncStatus) {
        this.syncStatus = syncStatus;
    }

    public LocalDateTime getSyncAt() {
        return syncAt;
    }

    public void setSyncAt(LocalDateTime syncAt) {
        this.syncAt = syncAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
