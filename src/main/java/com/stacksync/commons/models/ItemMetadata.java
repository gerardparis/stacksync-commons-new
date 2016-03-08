package com.stacksync.commons.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ItemMetadata implements Serializable {

	private static final long serialVersionUID = -2494445120408291949L;

	private UUID id;
	private UUID tempId;
	private Long version;
	private UUID deviceId;
	private UUID workspaceId;
	private UUID parentId;
	private Long parentVersion;

	private String status;
	private Date modifiedAt;
	private Long checksum;
	private List<String> chunks;
	private Long size;
	private Boolean isFolder;
	private String filename;
	private String mimetype;

	private Integer level; // for API calls
	private Boolean isRoot; // for API calls
	private List<ItemMetadata> children;

	public ItemMetadata() {
		this.isRoot = false;
		this.chunks = new ArrayList<String>();
	}

	public ItemMetadata(UUID id, Long version, UUID deviceId, UUID parentId,
			long parentVersion, String status, Date modifiedAt, Long checksum,
			Long size, boolean isFolder, String filename, String mimetype,
			List<String> chunks) {

		this.id = id;
		this.version = version;
		this.deviceId = deviceId;
		this.parentId = parentId;
		this.parentVersion = parentVersion;

		this.status = status;
		this.modifiedAt = modifiedAt;
		this.checksum = checksum;
		this.size = size;
		this.isFolder = isFolder;
		this.filename = filename;
		this.mimetype = mimetype;

		this.isRoot = false;

		if (chunks == null) {
			this.chunks = new ArrayList<String>();
		}
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getTempId() {
		return tempId;
	}

	public void setTempId(UUID tempId) {
		this.tempId = tempId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public UUID getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(UUID deviceId) {
		this.deviceId = deviceId;
	}
	
	public UUID getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(UUID workspaceId) {
		this.workspaceId = workspaceId;
	}

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}

	public Long getParentVersion() {
		return parentVersion;
	}

	public void setParentVersion(Long parentVersion) {
		this.parentVersion = parentVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<String> getChunks() {
		return chunks;
	}

	public void setChunks(List<String> chunks) {
		this.chunks = chunks;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Boolean isFolder() {
		return isFolder;
	}

	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean isRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public List<ItemMetadata> getChildren() {
		if (this.children == null)
			return new ArrayList<ItemMetadata>();

		return this.children;
	}

	public void setChildren(List<ItemMetadata> children) {
		this.children = children;
	}

	public void addChild(ItemMetadata itemMetadata) {
		if (this.children == null)
			this.children = new ArrayList<ItemMetadata>();
		this.children.add(itemMetadata);
	}

	public boolean equals(ItemMetadata metadata) {

		if (!metadata.getId().equals(this.id)
				|| !metadata.getDeviceId().equals(this.deviceId)
				|| !metadata.getChecksum().equals(this.checksum)
				|| !metadata.getSize().equals(this.size)) {
			return false;
		}

		if (!metadata.getStatus().equals(this.status)
				|| !metadata.getFilename().equals(this.filename)
				|| !metadata.getMimetype().equals(this.mimetype)) {
			return false;
		}

		if (!metadata.getVersion().equals(this.version)) {
			return false;
		}

		if (metadata.isFolder() != this.isFolder) {
			return false;
		}

		if (this.parentId != null && metadata.getParentId() != null) {
			if (!metadata.getParentId().equals(this.parentId)) {
				return false;
			}
		}

		if (this.parentVersion != null && metadata.getParentVersion() != null) {
			if (!metadata.getParentVersion().equals(this.parentVersion)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != getClass())
			return false;

		return ((ItemMetadata) obj).id == this.id;
	}

	@Override
	public String toString() {

		String format = "ItemMetadata: {id=%s, version=%s, filename=%s, chunks=%s}";
		String result = String.format(format, id, version, filename, chunks.size());

		return result;
	}
}
