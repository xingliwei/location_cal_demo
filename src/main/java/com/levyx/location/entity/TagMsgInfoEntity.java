package com.levyx.location.entity;

/**
 * 标签信息
 */
public class TagMsgInfoEntity {

    // &&&:0001:76 $

    // sync0rx:基站1收到的第0条信息的时间戳     sync0tx:同步节点发送的第0条时间戳
    // 16BD143DB4                           :   D6EEA24C00  $

    // sync1rx:基站1收到的第1条信息的时间戳     sync1tx:同步节点发送的第1条时间戳
    // 177B8206DE                           :   D7AD104C00  $
    // T:50:0005    $

    //tag_receive 基站收到tag的时间
    // 171C4B223E   $
    // 67

    //基站id
    private String authorId;

    //标签id
    private String tagId;

    //可能是标签发送数据的一个自增唯一序列
    private long tagSeq;

    //映射的时间戳
    private long tag_timestamp;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public long getTagSeq() {
        return tagSeq;
    }

    public void setTagSeq(long tagSeq) {
        this.tagSeq = tagSeq;
    }

    public long getTag_timestamp() {
        return tag_timestamp;
    }

    public void setTag_timestamp(long tag_timestamp) {
        this.tag_timestamp = tag_timestamp;
    }



    @Override
    public String toString() {
        return "TagMsgInfoEntity{" +
                "authorId='" + authorId + '\'' +
                ", tagId='" + tagId + '\'' +
                ", tagSeq=" + tagSeq +
                ", tag_timestamp=" + tag_timestamp +
                '}';
    }
}
