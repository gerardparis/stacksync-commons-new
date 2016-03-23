package com.stacksync.commons.models;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Embeddable
@Table(name = "item_version_chunk")
public class Chunk implements Serializable {
      
    @Type(type = "integer")
    @Column(nullable = false, insertable = false, updatable = false)
    private Integer order = null;

    @Type(type = "string")
    @Column(nullable = false)
    private String clientChunkName = null;

    public Chunk() {
    }

    public Chunk(String name, Integer order) {
        this.clientChunkName = name;
        this.order = order;
    }
        
    public Chunk(String name, Integer order, ItemVersion itemVersion) {
        this.clientChunkName = name;
        this.order = order;
    }
    
    public Integer getOrder() {
        return order;
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
