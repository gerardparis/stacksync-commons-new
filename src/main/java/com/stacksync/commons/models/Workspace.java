package com.stacksync.commons.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "workspace")
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

    @OneToOne(mappedBy = "userWorkspaces", cascade = CascadeType.ALL)
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
    
    @OneToMany(mappedBy = "workspace",  fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items;

    @Type(type = "string")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
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
        this.items = new ArrayList<Item>();
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
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeObject(Item object) {
        this.items.remove(object);
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
        /*
        List<User> users = new ArrayList<User>();

        for (UserWorkspace userWorkspace : workspaceUsers) {
            users.add(userWorkspace.getUser());
        }

        return users;
        */
        return null;
    }

    public void setUsers(List<User> users) {

        /*
        UserWorkspace workspaceOwner = null;
        for (UserWorkspace userWorkspace : workspaceUsers) {
            if (userWorkspace.getUser().getId().equals(owner.getId())) {
                workspaceOwner = userWorkspace;
                break;
            }
        }

        for (User user : users) {
            if (!name.isEmpty()) {
                workspaceUsers.add(new UserWorkspace(user, this, name, parentItem));
            } else {
                addUser(user, workspaceOwner);
            }

        }
        */
        
    }

    public void addUser(User user) {

        /*
        UserWorkspace workspaceOwner = null;
        for (UserWorkspace userWorkspace : workspaceUsers) {
            if (userWorkspace.getUser().getId().equals(owner.getId())) {
                workspaceOwner = userWorkspace;
                break;
            }
        }

        if (!name.isEmpty()) {
            workspaceUsers.add(new UserWorkspace(user, this, name, parentItem));
        } else {
            addUser(user, workspaceOwner);
        }
        */

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
        /*
        int i = 0;
        while (i < workspaceUsers.size()) {
            if (workspaceUsers.get(i).getUser().getId().equals(user.getId())) {
                workspaceUsers.remove(i);
                i = Integer.MAX_VALUE;
            }
            i++;
        }
        */
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
