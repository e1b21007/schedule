package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EntryMapper {
    /**
     * groupidからentryTableを取得する
     * @param groupid int グループID
     * @return ArrayList<entry> groupid-useridのリスト
     */
    @Select("SELECT USERID, GROUPID FROM ENTRY WHERE GROUPID = #{groupid}")
    ArrayList<Entry> getEntryByGroupid(int groupid);

    /**
     * useridからentryTableを取得する
     * @param userid int エントリー情報
     * @return ArrayList<entry> groupid-useridのリスト
     */
    @Select("SELECT USERID, GROUPID FROM ENTRY WHERE USERID = #{userid}")
    ArrayList<Entry> getEntryByUserid(int userid);

}
