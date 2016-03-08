package com.stacksync.commons.notifications;

import java.io.Serializable;
import java.util.UUID;

public class ShareProposalNotification extends Notification implements Serializable {

	protected static final long serialVersionUID = -3210149693220010223L;

	protected UUID workspaceId;
	protected String folderName;
	protected UUID itemId;
	protected UUID ownerId;
	protected String ownerName;
	protected String swiftContainer;
	protected String swiftURL;
	protected boolean isEncrypted;

	public ShareProposalNotification(UUID workspaceId, String folderName, UUID itemId, UUID ownerId,
			String ownerName, String swiftContainer, String swiftUrl, boolean isEncrypted) {
		super();
		this.workspaceId = workspaceId;
		this.folderName = folderName;
		this.itemId = itemId;
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.swiftContainer = swiftContainer;
		this.swiftURL = swiftUrl;
		this.isEncrypted = isEncrypted;
	}

	public UUID getWorkspaceId() {
		return workspaceId;
	}

	public void setWorkspaceId(UUID workspaceId) {
		this.workspaceId = workspaceId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public UUID getItemId() {
		return itemId;
	}

	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}

	public String getSwiftContainer() {
		return swiftContainer;
	}

	public void setSwiftContainer(String swiftContainer) {
		this.swiftContainer = swiftContainer;
	}

	public String getSwiftURL() {
		return swiftURL;
	}

	public void setSwiftURL(String swiftURL) {
		this.swiftURL = swiftURL;
	}

	public UUID getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}
	

}
