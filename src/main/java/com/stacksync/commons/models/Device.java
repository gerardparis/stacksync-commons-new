package com.stacksync.commons.models;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "device")
public class Device implements Serializable {

    private static final long serialVersionUID = -2932481953197148130L;

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Type(type = "string")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Type(type = "string")
    @Column(name = "os", nullable = false, length = 100)
    private String os;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Type(type = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_access_at", nullable = false)
    private Date lastAccessAt;

    @Type(type = "string")
    @Column(name = "last_ip", nullable = false, length = 100)
    private String lastIp;

    @Type(type = "string")
    @Column(name = "app_version", nullable = false, length = 45)
    private String appVersion;

    public Device() {
        this(null);
    }

    public Device(UUID id) {
        this(id, null, null);
    }

    public Device(UUID id, String name, User user) {
        this(name, user, null, null, null, null, null);
        this.id = id;
    }

    public Device(String name, User user, String os, Date createdAt,
            Date lastAccessAt, String lastIp, String appVersion) {
        this.name = name;
        this.user = user;
        this.os = os;
        this.createdAt = createdAt;
        this.lastAccessAt = lastAccessAt;
        this.lastIp = lastIp;
        this.appVersion = appVersion;
        this.createdAt = (createdAt == null) ? new Timestamp(System.currentTimeMillis()) : createdAt;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastAccessAt() {
        return lastAccessAt;
    }

    public void setLastAccessAt(Date lastAccessAt) {
        this.lastAccessAt = lastAccessAt;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValid() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.getClass().getName());
        result.append(" {");

        // determine fields declared in this class only (no fields of
        // superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        // print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                // requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
        }
        result.append("}");

        return result.toString();
    }

}
