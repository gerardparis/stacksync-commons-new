package com.stacksync.commons.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "stacksync")
public class Workspace implements Serializable {

    private static final long serialVersionUID = 243350300638953723L;

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Type(type = "integer")
    @Column(name = "latest_revision", nullable = false)
    private Integer latestRevision;

    @Embedded
    @Column(name = "owner", nullable = false)
    private User owner;
    
    @Type(type = "string")
    @Column(name = "swift_container", nullable = false, length = 45)
    private String swiftContainer;

    @Type(type = "string")
    @Column(name = "swift_url", nullable = false, length = 250)
    private String swiftUrl;

    @Type(type = "true_false")
    @Column(name = "is_shared", nullable = false)
    private boolean isShared = false;

    @Type(type = "true_false")
    @Column(name = "is_Encrypted", nullable = false)
    private boolean isEncrypted = false;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<UUID, Device> devices;
    
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<UUID,Item> items;

    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String,ItemVersion> itemVersions;
        
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String,Chunk> itemVersionChunks;
        
    @Type(type = "string")
    private String name;

    @Embedded
    @Column(name = "parent_item", nullable = true)
    private Item parentItem;
        
    public Workspace() {
        this(null);
    }

    public Workspace(UUID id) {
        this(id, 0, null, false, false);
    }

    public Workspace(UUID id, Integer latestRevision, User owner, boolean isShared, boolean isEncrypted) {
        this.id = id;
        this.latestRevision = latestRevision;
        this.owner = owner;
        this.isShared = isShared;
        this.isEncrypted = isEncrypted;
        this.items = new HashMap<UUID,Item>();
        this.devices = new HashMap<UUID,Device>();
        this.itemVersions = new HashMap<String,ItemVersion>();
        this.itemVersionChunks = new HashMap<String,Chunk>();
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.latestRevision = (latestRevision == null) ? 0 : latestRevision;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getLatestRevision() {
        return latestRevision;
    }

    public void setLatestRevision(Integer latestRevision) {
        this.latestRevision = latestRevision;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(Boolean isShared) {
        this.isShared = isShared;
    }
    
    public void addDevice(Device device) {
        this.devices.put(device.getId(),device);
    }
    
    public Device getDevice(UUID deviceId){
        return devices.get(deviceId);
    }

    public void removeDevice(Device device) {
        this.devices.remove(device.getId());
    }
    
    public ItemVersion getItemVersion(UUID itemId, Long version) {
        return itemVersions.get(itemId.toString()+version.toString());
    }

    public void putItemVersion(UUID itemId, ItemVersion itemVersion) {
        itemVersions.put(itemId.toString()+itemVersion.getVersion().toString(), itemVersion);
    }
    
    public List<Chunk> getVersionChunks(UUID itemId, Long version) {
        ItemVersion itemVersion = itemVersions.get(itemId.toString()+version.toString());
        
        ArrayList<Chunk> chunks = new ArrayList<Chunk>();
        
        for(int i=0; i<itemVersion.getChunksNumber(); i++){
            chunks.add(itemVersionChunks.get(itemId.toString()+version.toString()+i));
        }
        return chunks;
    }

    public void putVersionChunks(UUID itemId, Long version, List<Chunk> chunks) {
        for(int i=0; i<chunks.size(); i++){
            itemVersionChunks.put(itemId.toString()+version.toString()+i, chunks.get(i));
        }
    }
    

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    public String getSwiftContainer() {
        return swiftContainer;
    }

    public void setSwiftContainer(String swiftContainer) {
        this.swiftContainer = swiftContainer;
    }

    public String getSwiftUrl() {
        return swiftUrl;
    }

    public void setSwiftUrl(String swiftUrl) {
        this.swiftUrl = swiftUrl;
    }

    public List<Item> getItems() {
        return new ArrayList<Item>(items.values());
    }

    public void setItems(HashMap<UUID,Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.put(item.getId(),item);
    }

    public void removeObject(Item object) {
        this.items.remove(object.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getParentItem() {
        return parentItem;
    }

    public void setParentItem(Item parentItem) {
        this.parentItem = parentItem;
    }

    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(owner);
        return users;
    }

    public void setUsers(HashMap<UUID,User> users) {
        //this.users = users;
    }

    public void addUser(User user) {
       //users.put(user.getId(),user);
    }
    
    public User getUser(UUID userId){
        if(owner.getId().equals(userId)){
            return owner;
        } else {
            return null;
        }
    }
    
    public Item getItem(UUID itemId){
        return items.get(itemId);
    }
    /*
    public void addUser(User user, UserWorkspace workspaceOwner) {
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("User not valid");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Workspace not valid");
        }
        //FIX ME! not owner.getName, is workspace name
        workspaceUsers.add(new UserWorkspace(user, this, owner.getName(), parentItem));
    }

    public List<UserWorkspace> getWorkspaceUsers() {
        return workspaceUsers;
    }

    */
    
    public void removeUser(User user) {
        //users.remove(user.getId());
    }

    /**
     * Checks whether the user contains all required attributes (ID is not
     * required since it is assigned automatically when a user is inserted to
     * the database)
     *
     * @return Boolean True if the user is valid. False otherwise.
     */
    public boolean isValid() {
        if (this.owner == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        /*return String.format("workspace[id=%s, latestRevision=%s, owner=%s, items=%s, users=%s]", id,
                latestRevision, owner, items, workspaceUsers);*/
        return String.format("workspace[id=%s, latestRevision=%s, owner=%s, items=%s]", id,
                latestRevision, owner, items);
    }
}
