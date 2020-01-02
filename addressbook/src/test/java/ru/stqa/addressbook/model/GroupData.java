package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "group_list")
public class GroupData {
    @Id
    @Column(name = "group_id")
    private int groupId;
    @Expose
    @Column(name = "group_name")
    private String groupName;
    @Expose
    @Column(name = "group_header")
    @Type(type = "text")
    private String groupHeader;
    @Expose
    @Column(name = "group_footer")
    @Type(type = "text")
    private String groupFooter;

    public GroupData() {
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }

    public int getGroupId() {
        return groupId;
    }

    public GroupData withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupData withGroupHeader(String groupHeader) {
        this.groupHeader = groupHeader;
        return this;
    }

    public GroupData withGroupFooter(String groupFooter) {
        this.groupFooter = groupFooter;
        return this;
    }

    public GroupData withGroupId(int groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupHeader='" + groupHeader + '\'' +
                ", groupFooter='" + groupFooter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return groupId == groupData.groupId &&
                Objects.equals(groupName, groupData.groupName) &&
                Objects.equals(groupHeader, groupData.groupHeader) &&
                Objects.equals(groupFooter, groupData.groupFooter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, groupName, groupHeader, groupFooter);
    }
}
