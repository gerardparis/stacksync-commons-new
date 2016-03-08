package com.stacksync.commons.models;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "item_version_chunk")
public class Chunk implements Serializable {
    
    /*
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    */
    
    @EmbeddedId 
    //Any overrides to simple Id properties should be handled with an attribute override
    @AttributeOverride(name = "order", column = @Column(name = "order"))
    ChunkKey id = new ChunkKey();
    
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("itemVersionId")
    private ItemVersion itemVersion;

    @Type(type = "integer")
    @Column(nullable = false, insertable = false, updatable = false)
    private Integer order = null;

    @Type(type = "string")
    @Column(nullable = false)
    private String clientChunkName = null;

    public Chunk() {
    }

    public ItemVersion getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(ItemVersion itemVersion) {
        this.itemVersion = itemVersion;
    }

    public Chunk(String name, Integer order, ItemVersion itemVersion) {
        this.clientChunkName = name;
        this.order = order;
        this.itemVersion = itemVersion;
    }
        
    public Chunk(String name, Integer order) {
        this.clientChunkName = name;
        this.order = order;
    }

    public void setId(ChunkKey key){
        id = key;
    }
    
    public Integer getOrder() {
        return order;
    }
    
    public ChunkKey getId(){
        return id;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getClientChunkName() {
        return clientChunkName;
    }

    public void setClientChunkName(String clientChunkName) {
        this.clientChunkName = clientChunkName;
    }

    public boolean isValid() {
        //TODO: Unimplemented method
        return true;
    }

    @Override
    public String toString() {
        String format = "Chunk[clientChunkName=%s, order=%s]";
        String result = String.format(format, clientChunkName, order);

        return result;
    }
}
