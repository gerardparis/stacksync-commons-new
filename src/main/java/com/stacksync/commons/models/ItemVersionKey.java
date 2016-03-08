/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stacksync.commons.models;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.annotations.Type;

/**
 *
 * @author marcruiz
 */
@Embeddable
public class ItemVersionKey implements Serializable{

    Long version;
    @Type(type = "uuid-char")
    UUID itemId;
    
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    
    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.version != null ? this.version.hashCode() : 0);
        hash = 37 * hash + (this.itemId != null ? this.itemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemVersionKey other = (ItemVersionKey) obj;
        if (this.version != other.version && (this.version == null || !this.version.equals(other.version))) {
            return false;
        }
        if (this.itemId != other.itemId && (this.itemId == null || !this.itemId.equals(other.itemId))) {
            return false;
        }
        return true;
    }
    
    
}
