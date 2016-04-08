package com.stacksync.commons.models;

import java.io.Serializable;

public class Chunk implements Serializable {
    
    private Integer order = null;

    private String clientChunkName = null;

    public Chunk() {
    }

    public ItemVersion getItemVersion() {
        //return itemVersion;
        return null;
    }

    public void setItemVersion(ItemVersion itemVersion) {
        //this.itemVersion = itemVersion;
    }

    public Chunk(String name, Integer order, ItemVersion itemVersion) {
        this.clientChunkName = name;
        this.order = order;
        //this.itemVersion = itemVersion;
    }
        
    public Chunk(String name, Integer order) {
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
