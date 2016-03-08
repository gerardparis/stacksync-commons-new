package com.stacksync.commons.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.Index;

@Entity
@Table(name = "user1")
@Indexed
public class User implements Serializable {

    private static final long serialVersionUID = -8827608629982195900L;

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Type(type = "string")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Type(type = "string")
    @Column(name = "swift_user", nullable = false, unique = true, length = 100)
    private String swiftUser;

    @Type(type = "string")
    @Column(name = "swift_account", nullable = false, length = 100)
    private String swiftAccount;

    @Type(type = "string")
    @Field(index=Index.YES, analyze=Analyze.NO, store=Store.NO)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Type(type = "long")
    @Column(name = "quota_limit", nullable = false)
    private Long quotaLimit;

    @Type(type = "long")
    @Column(name = "quota_used_real", nullable = false)
    private Long quotaUsedReal;

    @Type(type = "long")
    @Column(name = "quota_used_logical", nullable = false)
    private Long quotaUsedLogical;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Device> devices;

    @OneToOne
    private Workspace userWorkspaces;

    public User() {
        this(null);
    }

    public User(UUID id) {
        this(id, null, null, null, null, null, null, null);
    }

    public User(UUID id, String name, String swiftUser, String swiftAccount, String email, Long quotaLimit, Long quotaUsedLogical, Long quotaUsedReal) {
        this.id = id;
        this.name = name;
        this.swiftUser = swiftUser;
        this.swiftAccount = swiftAccount;
        this.email = email;
        this.quotaLimit = quotaLimit;
        this.quotaUsedLogical = quotaUsedLogical;
        this.quotaUsedReal = quotaUsedReal;
        this.devices = new ArrayList<Device>();
        this.quotaLimit = (quotaLimit == null) ? 0 : quotaLimit;
        this.quotaUsedReal = (quotaUsedReal == null) ? 0 : quotaUsedReal;
        this.quotaUsedLogical = (quotaUsedLogical == null) ? 0 : quotaUsedLogical;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwiftUser() {
        return swiftUser;
    }

    public void setSwiftUser(String swiftUser) {
        this.swiftUser = swiftUser;
    }

    public String getSwiftAccount() {
        return swiftAccount;
    }

    public void setSwiftAccount(String swiftAccount) {
        this.swiftAccount = swiftAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getQuotaLimit() {
        return quotaLimit;
    }

    public void setQuotaLimit(Long quotaLimit) {
        this.quotaLimit = quotaLimit;
    }

    public Long getQuotaUsedReal() {
        return quotaUsedReal;
    }

    public void setQuotaUsedReal(Long quotaUsed) {
        this.quotaUsedReal = quotaUsed;
    }

    public Long getQuotaUsedLogical() {
        return quotaUsedLogical;
    }

    public void setQuotaUsedLogical(Long quotaUsedLogical) {
        this.quotaUsedLogical = quotaUsedLogical;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        this.devices.add(device);
    }

    public void removeDevice(Device device) {
        this.devices.remove(device);
    }

    public List<Workspace> getWorkspaces() {

        /*List<Workspace> workspaces = new ArrayList<Workspace>();

        for (UserWorkspace userWorkspace : userWorkspaces) {
            workspaces.add(userWorkspace.getWorkspace());
        }
        return workspaces;
        */
            
        List<Workspace> workspaces = new ArrayList<Workspace>();
        
        workspaces.add(userWorkspaces);
        
        return workspaces;
    }
    
    
    public Workspace getUserWorkspaces() {
        return userWorkspaces;
    }

    public void setUserWorkspaces(Workspace userWorkspaces) {
        this.userWorkspaces = userWorkspaces;
    }

    public void setWorkspaces(List<Workspace> workspaces) {
        //TODO
    }

    public void addWorkspace(Workspace workspace) {
        userWorkspaces = workspace;
    }

    public void removeWorkspace(Workspace workspace) {
        //TODO
    }

    @Override
    public boolean equals(Object obj) {
        // if the two objects are equal in reference, they are equal
        if (this == obj) {
            return true;
        } else if (obj instanceof User) {
            User user = (User) obj;
            if (((user.getId() == null) && (this.getId() == null))
                    || user.getId().equals(this.getId())
                    && ((user.getName() == null) && (this.getName() == null) || user.getName().equals(this.getName()))
                    && ((user.getSwiftUser() == null) && (this.getSwiftUser() == null) || user.getSwiftUser().equals(
                            this.getSwiftUser()))
                    && ((user.getEmail() == null) && (this.getEmail() == null) || user.getEmail().equals(
                            this.getEmail()))
                    && ((user.getQuotaLimit() == null) && (this.getQuotaLimit() == null) || user.getQuotaLimit()
                    .equals(this.getQuotaLimit()))
                    && ((user.getQuotaUsedLogical() == null) && (this.getQuotaUsedLogical() == null) || user.getQuotaUsedLogical().equals(
                            this.getQuotaUsedLogical()))
                    && ((user.getQuotaUsedReal() == null) && (this.getQuotaUsedReal() == null) || user.getQuotaUsedReal().equals(
                            this.getQuotaUsedReal()))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        
        return String.format("User[id=%s, name=%s, swiftUser=%s, swiftAccount=%s, email=%s, quotaLimit=%s, quotaUsedLogial=%s, quotaUsedReal=%s]", id, name,
        swiftUser, swiftAccount, email, quotaLimit, quotaUsedLogical, quotaUsedReal);

        /*return String.format("User[id=%s, name=%s, swiftUser=%s, swiftAccount=%s, email=%s, quotaLimit=%s, quotaUsedLogial=%s, quotaUsedReal=%s]", id, name,
                swiftUser, swiftAccount, email, quotaLimit, quotaUsedLogical, quotaUsedReal); */
    }

    /**
     * Checks whether the user contains all required attributes (ID is not
     * required since it is assigned automatically when a user is inserted to
     * the database)
     *
     * @return Boolean True if the user is valid. False otherwise.
     */
    public boolean isValid() {
        if (this.swiftUser == null || this.email == null || this.name == null || this.quotaLimit == null
                || this.quotaUsedLogical == null || this.quotaUsedReal == null) {
            return false;
        } else {
            return true;
        }
    }

}
