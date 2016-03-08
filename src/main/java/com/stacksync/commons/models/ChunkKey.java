/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stacksync.commons.models;

import java.io.Serializable;
import javax.persistence.Embeddable;
import org.hibernate.annotations.Type;

/**
 *
 * @author marcruiz
 */
@Embeddable
public class ChunkKey implements Serializable{
    Integer order;
    ItemVersionKey itemVersionId;
    
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    public ItemVersionKey getItemVersionId() {
        return itemVersionId;
    }

    public void setItemVersionId(ItemVersionKey itemVersionId) {
        this.itemVersionId = itemVersionId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.order != null ? this.order.hashCode() : 0);
        hash = 97 * hash + (this.itemVersionId != null ? this.itemVersionId.hashCode() : 0);
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
        final ChunkKey other = (ChunkKey) obj;
        if (this.order != other.order && (this.order == null || !this.order.equals(other.order))) {
            return false;
        }
        if (this.itemVersionId != other.itemVersionId && (this.itemVersionId == null || !this.itemVersionId.equals(other.itemVersionId))) {
            return false;
        }
        return true;
    }
    
    
}
