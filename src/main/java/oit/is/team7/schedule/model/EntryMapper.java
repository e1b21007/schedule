package oit.is.team7.schedule.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

@Mapper
public interface EntryMapper {
    /**
     * groupidからentryTableを取得する
     * @param groupid int グループID
     * @return ArrayList<entry> groupid-useridのリスト
     */
    @Select("SELECT USERID, GROUPID FROM ENTRY WHERE GROUPID = #{groupid}")
    ArrayList<Entry> selectEntryByGroupid(int groupid);

    /**
     * useridからentryTableを取得する
     * @param userid int エントリー情報
     * @return ArrayList<entry> groupid-useridのリスト
     */
    @Select("SELECT USERID, GROUPID FROM ENTRY WHERE USERID = #{userid}")
    ArrayList<Entry> selectEntryByUserid(int userid);

    /**
     * entryTableを追加する
     * @param userid int エントリーしているユーザー
     * @param grouprid int エントリー先のグループ
     * @return void
     */
    @Insert("INSERT INTO entry (userid, groupid) VALUES (#{userid}, #{groupid});")
    void InsertEntry(int userid, int groupid);

    /**
     * entrytableを全て取得する
     * @return ArrayList<entry> groupid-useridのリスト
     */
    @Select("SELECT USERID, GROUPID FROM ENTRY")
    ArrayList<Entry> selectAllEntry();

    @Delete("Delete From Entry Where GroupId = #{GroupId};")
    void DeleteEntryByGroupId(int GroupId);

}
