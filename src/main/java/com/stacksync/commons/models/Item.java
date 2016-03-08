package com.stacksync.commons.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1482457936400001556L;

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private Workspace workspace;

    @Type(type = "long")
    @Column(name = "latest_version", nullable = false)
    private Long latestVersion;

    @OneToOne(cascade = CascadeType.ALL)
    private Item parent;

    @Type(type = "string")
    @Column(name = "filename", nullable = false, length = 100)
    private String filename;

    @Type(type = "string")
    @Column(name = "mimetype", nullable = false, length = 150)
    private String mimetype;

    @Type(type = "true_false")
    @Column(name = "is_folder", nullable = false)
    private Boolean isFolder;

    @Type(type = "long")
    @Column(name = "client_parent_file_version", nullable = true)
    private Long clientParentFileVersion;

    @OneToMany(mappedBy="item", cascade = CascadeType.ALL)
    private List<ItemVersion> versions;

    public Item() {
        this(null);
    }

    public Item(UUID id) {
        this(id, null, null, null, null, null, null, null, null);
    }

    public Item(UUID id, Workspace workspace, Long latestVersion, Item parent, Long clientFileId,
            String filename, String mimetype, Boolean isFolder,
            Long clientParentFileVersion) {

        this.id = id;
        this.workspace = workspace;
        this.latestVersion = latestVersion;
        this.parent = parent;
        this.filename = filename;
        this.mimetype = mimetype;
        this.isFolder = isFolder;
        this.clientParentFileVersion = clientParentFileVersion;
        this.versions = new ArrayList<ItemVersion>();
    }

    public UUID getId() {
        return id;
    }

    public UUID setId(UUID id) {
        return this.id = id;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public Long getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Long latestVersion) {
        this.latestVersion = latestVersion;
    }

    public Item getParent() {
        return parent;
    }

    public UUID getParentId() {
        if (parent == null) {
            return null;
        } else {
            return parent.getId();
        }
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public Boolean isFolder() {
        return isFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.isFolder = isFolder;
    }

    public Long getClientParentFileVersion() {
        return clientParentFileVersion;
    }

    public void setClientParentFileVersion(Long clientParentFileVersion) {
        this.clientParentFileVersion = clientParentFileVersion;
    }

    public List<ItemVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<ItemVersion> versions) {
        this.versions = versions;
    }

    public void addVersion(ItemVersion objectVersion) {
        this.versions.add(objectVersion);
    }

    public void removeVersion(ItemVersion objectVersion) {
        this.versions.remove(objectVersion);
    }

    public boolean hasParent() {

        boolean has = true;
        if (this.parent == null) {
            has = false;
        }
        return has;
    }

    /**
     * Returns True if the {@link Item} is valid. False otherwise.
     *
     * @return Boolean indicating whether the {@link Item} is valid or not.
     */
    public boolean isValid() {

        if (workspace == null || latestVersion == null
                || filename == null || mimetype == null || isFolder == null || versions == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String format = "Item[id=%s, parentId=%s, workspaceId=%s, latestVersion=%s, "
                + "Filename=%s, "
                + "mimetype=%s, isFolder=%s, "
                + "clientParentFileVersion=%s, versions=%s]";

        UUID parentId = null;
        if (parent != null) {
            parentId = parent.getId();
        }

        UUID workspaceId = null;
        if (workspace != null) {
            workspaceId = workspace.getId();
        }

        Integer versionsSize = null;
        if (versions != null) {
            versionsSize = versions.size();
        }

        String result = String.format(format, id, parentId, workspaceId, latestVersion,
                filename, mimetype, isFolder,
                clientParentFileVersion, versionsSize);

        return result;
    }

}
