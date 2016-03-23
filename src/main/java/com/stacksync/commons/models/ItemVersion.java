package com.stacksync.commons.models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "item_version")
public class ItemVersion implements Serializable {

    /*@Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    */
    
    
    @EmbeddedId 
    //Any overrides to simple Id properties should be handled with an attribute override
    @AttributeOverride(name = "version", column = @Column(name = "version"))
    ItemVersionKey id = new ItemVersionKey();
    
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("itemId")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL)
    private Device device;

    @Type(type = "long")
    @Column(name = "version", nullable = false, insertable = false, updatable = false)
    private Long version;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "commited_at", nullable = false)
    private Date committedAt;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", nullable = false)
    private Date modifiedAt;

    @Type(type = "long")
    @Column(name = "checksum", nullable = false)
    private Long checksum;

    @Type(type = "string")
    @Column(name = "status", nullable = false, length = 10)
    private String status;

    @Type(type = "long")
    @Column(name = "size", nullable = false)
    private Long size;

    @ElementCollection
    private List<Chunk> chunks;

    public ItemVersion() {
        this.id = null;
    }

    public ItemVersion(Item item, Device device, Long version, Date committedAt,
            Date modifiedAt, Long checksum, String status, Long size) {
        this.item = item;
        this.device = device;
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
        this.item = new Item(metadata.getId());
        this.device = new Device(metadata.getDeviceId());
        this.version = metadata.getVersion();
        this.modifiedAt = metadata.getModifiedAt();
        this.checksum = metadata.getChecksum();
        this.status = metadata.getStatus();
        this.size = metadata.getSize();
    }

    public ItemVersionKey getId() {
        return id;
    }

    public void setId(ItemVersionKey id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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

    public List<Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(List<Chunk> chunks) {
        this.chunks = chunks;
    }

    public boolean isValid() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String toString() {
        String format = "ItemVersion[id=%s, itemId=%s, version=%s, chunks=%s, deviceId=%s, modifiedAt=%s, "
                + "committedAt=%s, checksum=%s, status=%s, "
                + "size=%s]";

        UUID itemId = null;
        if (item != null) {
            itemId = item.getId();
        }

        Integer chunksSize = null;
        if (chunks != null) {
            chunksSize = chunks.size();
        }

        UUID deviceId = null;
        if (device != null) {
            deviceId = device.getId();
        }

        String result = String.format(format, id, itemId, version, chunksSize, deviceId, modifiedAt,
                committedAt, checksum, status, size);

        return result;
    }
}
