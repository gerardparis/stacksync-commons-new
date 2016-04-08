package com.stacksync.commons.models;

import java.io.Serializable;
import java.util.UUID;

public class Item implements Serializable {

    private static final long serialVersionUID = 1482457936400001556L;

    private UUID id;

    private Long latestVersion;

    private UUID parent;
    
    private String filename;

    private String mimetype;

    private Boolean isFolder;

    private Long clientParentFileVersion;

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
        //this.workspace = workspace;
        this.latestVersion = latestVersion;
        if(parent!=null){
           this.parent = parent.getId(); 
        } else {
            this.parent = null;
        }
        
        this.filename = filename;
        this.mimetype = mimetype;
        this.isFolder = isFolder;
        this.clientParentFileVersion = clientParentFileVersion;
    }

    public UUID getId() {
        return id;
    }

    public UUID setId(UUID id) {
        return this.id = id;
    }

    public Workspace getWorkspace() {
        //return workspace;
        return null;
    }

    public void setWorkspace(Workspace workspace) {
        //this.workspace = workspace;
    }

    public Long getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Long latestVersion) {
        this.latestVersion = latestVersion;
    }

    public UUID getParent() {
        return parent;
    }

    public UUID getParentId() {
        if (parent == null) {
            return null;
        } else {
            return parent;
        }
    }

    public void setParent(Item parent) {
        this.parent = parent.getId();
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

        /*if (workspace == null || latestVersion == null
                || filename == null || mimetype == null || isFolder == null || versions == null) {
            return false;
        }*/

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
            parentId = parent;
        }

        UUID workspaceId = null;
        /*if (workspace != null) {
            workspaceId = workspace.getId();
        }*/

        Integer versionsSize = null;

        String result = String.format(format, id, parentId, workspaceId, latestVersion,
                filename, mimetype, isFolder,
                clientParentFileVersion, versionsSize);

        return result;
    }

}
