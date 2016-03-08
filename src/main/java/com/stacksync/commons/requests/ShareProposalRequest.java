package com.stacksync.commons.requests;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ShareProposalRequest extends Request implements Serializable {

	protected static final long serialVersionUID = -5985754297818659212L;

	protected List<String> emails;
	protected UUID itemId;
	protected boolean isEncrypted;

	public ShareProposalRequest(UUID userId, List<String> emails,
			UUID itemId, boolean isEncrypted) {
		super(userId);
		
		this.emails = emails;
		this.itemId = itemId;
		this.isEncrypted = isEncrypted;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public UUID getItemId() {
		return itemId;
	}

	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}
	
	

}
