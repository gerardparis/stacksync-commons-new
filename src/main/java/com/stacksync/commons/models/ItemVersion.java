package com.stacksync.commons.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.io.Serializable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

@Embeddable
public class ItemVersion implements Serializable {

    @Type(type = "uuid-char")
    private UUID device;

    @Type(type = "long")
    private Long version;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date committedAt;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

    @Type(type = "long")
    private Long checksum;

    @Type(type = "string")
    private String status;

    @Type(type = "long")
    private Long size;
    
    @Type(type = "long")
    private Long chunksNumber;

    public ItemVersion() {
        //this.id = null;
    }

    public ItemVersion(Device device, Long version, Date committedAt,
            Date modifiedAt, Long checksum, String status, Long size) {
        this.device = device.getId();
        this.version = version;
        this.committedAt = committedAt;
        this.modifiedAt = modifiedAt;
        this.checksum = checksum;
        this.status = status;
        this.size = size;
    }

    public ItemVersion(ItemMetadata metadata) {
        //TODO Check this assignation
        //this.id = metadata.getVersion();
        this.device = metadata.getDeviceId();
        this.version = metadata.getVersion();
        this.modifiedAt = metadata.getModifiedAt();
        this.checksum = metadata.getChecksum();
        this.status = metadata.getStatus();
        this.size = metadata.getSize();
    }

    public Long getChunksNumber() {
        return chunksNumber;
    }

    public void setChunksNumber(Long chunksNumber) {
        this.chunksNumber = chunksNumber;
    }

    public UUID getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device.getId();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getChecksum() {
        return checksum;
    }

    public void setChecksum(Long checksum) {
        this.checksum = checksum;
    }

    public Date getCommittedAt() {
        return committedAt;
    }

    public void setCommittedAt(Date committedAt) {
        this.committedAt = committedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public boolean isValid() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String toString() {
        String format = "ItemVersion[id=%s, itemId=%s, version=%s, deviceId=%s, modifiedAt=%s, "
                + "committedAt=%s, checksum=%s, status=%s, "
                + "size=%s]";

        UUID itemId = null;

        UUID deviceId = null;
        if (device != null) {
            deviceId = device;
        }

        String result = String.format(format, itemId, version, deviceId, modifiedAt,
                committedAt, checksum, status, size);

        return result;
    }
}
